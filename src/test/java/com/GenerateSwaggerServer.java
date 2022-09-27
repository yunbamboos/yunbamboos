package com;

import com.service.impl.login.LoginServiceImpl;
import com.service.impl.user.UserServiceImpl;
import com.service.inter.ILoginService;
import com.service.inter.IUserService;
import io.github.yunbamboos.rest.api.GenerateSwaggerBuilder;
import io.github.yunbamboos.rest.api.swagger.GenerateSwagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生成 Swagger JSON 文档
 */
public class GenerateSwaggerServer {

    private static final Logger log = LoggerFactory.getLogger(GenerateSwaggerServer.class);

    /**
     * /work/资料/data/swagger.openapi.json
     * E:\swagger.openapi.json
     */
    public static void main(String[] args) {
        GenerateSwagger swagger = new GenerateSwaggerBuilder()
                .file("E:\\movie.service.swagger.openapi.json")
                .primaryClass(MovieService.class)
                .build();
        // 登录相关接口
        swagger.addServiceApi(ILoginService.class, LoginServiceImpl.class);
        // 用户相关接口
        swagger.addServiceApi(IUserService.class, UserServiceImpl.class);
        swagger.generateSwaggerJSONFile();
        log.info("生成文件结束");
    }

}
