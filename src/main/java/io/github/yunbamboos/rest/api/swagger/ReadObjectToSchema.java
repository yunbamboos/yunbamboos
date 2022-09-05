package io.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;
import io.github.yunbamboos.rest.anno.RestServiceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ReadObjectToSchema {

    private static final Logger log = LoggerFactory.getLogger(ReadObjectToSchema.class);

    String type = "InDTO";

    BlockingQueue<ReadObjectToSchema> queue;

    RestServiceType restServiceType;

    String method;

    PathsNodeItem path;

    Schema.Properties properties;

    List<String> orders;

    List<String> requireds;

    Class<?> in;

    JSONObject example;

    public void read() {
        for (Field field : in.getDeclaredFields()) {
            ParamDesc paramDesc = field.getAnnotation(ParamDesc.class);
            if (paramDesc != null) {
                if ("GET".equals(method)) {
                    readGET(paramDesc);
                }
                if ("POST".equals(method)) {
                    readPOST(paramDesc, field.getType());
                }
            }
        }

    }

    private void readGET(ParamDesc paramDesc) {
        PathsNodeItem$ParametersItem pp = new PathsNodeItem$ParametersItem();
        pp.name = paramDesc.name();
        pp.in = "query";
        pp.description = paramDesc.zn();
        pp.required = true;
        pp.example = paramDesc.demo();
        pp.schema = new JSONObject();
        pp.schema.put("type", paramDesc.type().getType());
        path.method.parameters.add(pp);
        log.info("read service {} DTO:{} attr: {}({})", restServiceType.name(), type, paramDesc.zn(), paramDesc.name());
    }

    private void readPOST(ParamDesc paramDesc, Class<?> in) {
        Schema.Attr attr = new Schema.Attr();
        attr.name = paramDesc.name();
        attr.type = paramDesc.type().getType();
        attr.title = paramDesc.zn();
        attr.defaultValue = paramDesc.demo();
        attr.description = paramDesc.description();
        attr.required = paramDesc.isNull();

        if (paramDesc.type() == ParamType.String) {
            this.example.put(paramDesc.name(), paramDesc.demo());
        }
        if (paramDesc.type() == ParamType.Integer) {
            this.example.put(paramDesc.name(), Integer.parseInt(paramDesc.demo()));
        }
        if (paramDesc.type() == ParamType.Object) {
            attr.properties = new Schema.Properties();
            attr.orders = new ArrayList<>();
            attr.requireds = new ArrayList<>();

            JSONObject example = new JSONObject();
            this.example.put(paramDesc.name(), example);

            ReadObjectToSchema read = new ReadObjectToSchema();
            read.type = type;
            read.queue = queue;
            read.restServiceType = restServiceType;
            read.method = method;

            read.properties = attr.properties;
            read.orders = attr.orders;
            read.requireds = attr.requireds;

            read.in = in;
            read.example = example;
            queue.add(read);
        }
        properties.attrs.add(attr);
        orders.add(attr.name);
        if (attr.required) {
            requireds.add(attr.name);
        }
        log.info("read service {} DTO:{} attr: {}({})", restServiceType.name(), type, paramDesc.zn(), paramDesc.name());
    }

}
