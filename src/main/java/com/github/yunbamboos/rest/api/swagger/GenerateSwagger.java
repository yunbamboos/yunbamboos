package com.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.yunbamboos.dto.in.InDTO;
import com.github.yunbamboos.dto.out.OutDTO;
import com.github.yunbamboos.rest.anno.AppInfo;
import com.github.yunbamboos.rest.anno.RestServiceType;
import com.github.yunbamboos.rest.anno.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 生成swagger json 文档
 */
public class GenerateSwagger {

    private static final Logger log = LoggerFactory.getLogger(GenerateSwagger.class);

    /**
     * 生成文件位置
     */
    public String file;
    /**
     * 服务启动类 包含AppInfo注解
     */
    public Class<?> primaryClass;

    public List<ServiceApi> apiList;

    public GenerateSwagger() {
        this.apiList = new ArrayList<>();
    }

    public void addServiceApi(Class<?> interfaceClass, Class<?> serviceClass) {
        ServiceApi api = new ServiceApi();
        api.interfaceClass = interfaceClass;
        api.serviceClass = serviceClass;
        apiList.add(api);
    }

    public void generateSwaggerJSONFile() {
        Swagger swagger = generate();
        String out = JSON.toJSONString(swagger.getJSONObject(),
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect
        );
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(out.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
            log.info("生成文件:{}", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Swagger generate() {
        Swagger swagger = new Swagger();
        swagger.openapi = "3.0.1";
        this.readInfo(swagger);
        this.readServiceApi(swagger);
        return swagger;
    }

    /**
     * 读取项目信息
     */
    private void readInfo(Swagger swagger) {
        AppInfo appInfo = primaryClass.getAnnotation(AppInfo.class);
        if (appInfo != null) {
            swagger.info.title = appInfo.title();
            swagger.info.description = appInfo.description();
            swagger.info.version = appInfo.version();
        }
    }

    private void readServiceApi(Swagger swagger) {
        for (ServiceApi api : this.apiList) {
            log.info("read class:{}", api.serviceClass.getName());
            Tag tag = api.serviceClass.getAnnotation(Tag.class);
            assert tag != null;
            TagsNodeItem item = new TagsNodeItem();
            item.name = tag.value();
            swagger.tags.add(item);

            // 读取方法
            for (Method method : api.serviceClass.getMethods()) {
                RestServiceType restServiceType = method.getAnnotation(RestServiceType.class);
                if (restServiceType == null) {
                    continue;
                }
                log.info("read class:{} rest service:{}", api.serviceClass.getName(), restServiceType.url());
                PathsNodeItem path = new PathsNodeItem();
                path.url = restServiceType.url();
                path.method = new PathsNodeItem$Method(restServiceType.method());
                path.method.summary = restServiceType.name();
                path.method.folder = tag.value();
                path.method.deprecated = false;
                path.method.description = "";
                path.method.tags.add(tag.value());
                // get 入参参数解析
                if (restServiceType.method() == RequestMethod.GET) {
                    this.readInDTO("GET", restServiceType.in(), restServiceType, path);
                }
                // post
                if (restServiceType.method() == RequestMethod.POST) {
                    this.readInDTO("POST", restServiceType.in(), restServiceType, path);
                }
                this.readOutDTO(restServiceType.out(), restServiceType, path);
                swagger.paths.add(path);
            }
        }
    }

    private void readInDTO(String method, Class<?> inDTOClass, RestServiceType restServiceType, PathsNodeItem path) {
        List<Class<?>> classes = new ArrayList<>();
        classes.add(inDTOClass);
        Class<?> superClass = inDTOClass.getSuperclass();
        while (!superClass.equals(InDTO.class)) {
            classes.add(superClass);
            superClass = superClass.getSuperclass();
            if (superClass.equals(Object.class)) {
                break;
            }
        }
        classes.add(InDTO.class);
        if ("POST".equals(method)) {
            path.method.requestBody.content.applicationJson.root = new Schema.Root();
            path.method.requestBody.content.applicationJson.root.properties = new Schema.Properties();
            path.method.requestBody.content.applicationJson.root.orders = new ArrayList<>();
            path.method.requestBody.content.applicationJson.root.requireds = new ArrayList<>();
            path.method.requestBody.content.applicationJson.example = new JSONObject(new LinkedHashMap<>());
        }
        BlockingQueue<ReadObjectToSchema> queue = new ArrayBlockingQueue<>(100);
        for (int i = classes.size() - 1; i >= 0; --i) {
            ReadObjectToSchema read = new ReadObjectToSchema();
            read.queue = queue;
            read.restServiceType = restServiceType;
            read.method = method;
            read.path = path;
            read.properties = path.method.requestBody.content.applicationJson.root.properties;
            read.orders = path.method.requestBody.content.applicationJson.root.orders;
            read.requireds = path.method.requestBody.content.applicationJson.root.requireds;
            read.in = classes.get(i);
            read.example = path.method.requestBody.content.applicationJson.example;
            queue.add(read);
        }
        this.read(queue);
    }

    private void readOutDTO(Class<?> outDTOClass, RestServiceType restServiceType, PathsNodeItem path) {
        List<Class<?>> classes = new ArrayList<>();
        classes.add(outDTOClass);
        Class<?> superClass = outDTOClass.getSuperclass();
        while (!superClass.equals(OutDTO.class)) {
            classes.add(superClass);
            superClass = superClass.getSuperclass();
            if (superClass.equals(Object.class)) {
                break;
            }
        }
        BlockingQueue<ReadObjectToSchema> queue = new ArrayBlockingQueue<>(100);
        for (int i = classes.size() - 1; i >= 0; --i) {
            ReadObjectToSchema read = new ReadObjectToSchema();
            read.type="OutDTO";
            read.queue = queue;
            read.restServiceType = restServiceType;
            read.method = "POST";
            read.path = path;
            read.properties = path.method.responses.ok.content.applicationJson.data.properties;
            read.orders = path.method.responses.ok.content.applicationJson.data.orders;
            read.requireds = path.method.responses.ok.content.applicationJson.data.requireds;
            read.in = classes.get(i);
            read.example = path.method.responses.ok.content.applicationJson.dataExample;
            queue.add(read);
        }
        this.read(queue);
    }

    private void read(BlockingQueue<ReadObjectToSchema> queue){
        while (!queue.isEmpty()) {
            try {
                ReadObjectToSchema read = queue.poll(1, TimeUnit.MILLISECONDS);
                if (read != null) {
                    read.read();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
