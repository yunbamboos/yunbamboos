package io.github.yunbamboos.rest.anno;

import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.rest.filter.IRestServiceFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RestService 服务注解 实现RestService发布服务
 * <p>功能相当于springmvc RestController + RequestMapping 注解 将Controller的方法发布可以调用的http服务
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RestServiceType {

    /**
     * 请求方式
     */
    RequestMethod method() default RequestMethod.POST;

    /**
     * 调用的url路径
     */
    String url() default "";

    /**
     * 服务入参
     */
    Class<? extends InDTO> in();

    /**
     * 服务出参
     */
    Class<? extends OutDTO> out();

    /**
     * 服务中文名称
     */
    String name() default "";

    /**
     * 调用权限认证
     */
    boolean authentication() default true;

    /**
     * 自定义服务过滤器列表
     */
    Class<? extends IRestServiceFilter>[] filters() default {};


}
