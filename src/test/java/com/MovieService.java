package com;

import io.github.yunbamboos.application.Application;
import io.github.yunbamboos.rest.anno.RestServiceScan;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
/**
 * 服务启动类
 * */
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@MapperScan("com.mapper")
@ComponentScan(basePackages = {"com.mapper", "com.service"})
@RestServiceScan({"com.service.inter"})
public class MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    /**
     * 程序启动类
     * StartListener 在程序启动后执行
     * CloseListener 在程序关闭执行
     */
    public static void main(String[] args) {
        Application app = new Application(MovieService.class);
        app.run(args);
        log.info("app end");
    }

}
