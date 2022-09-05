package io.github.yunbamboos.rest.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * http 入参  出参数注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ParamDesc {
    /**
     * 名称 例如: user_id
     */
    String name();

    /**
     * 中文名称 例如: 用户ID
     */
    String zn();

    /**
     * 是否可空 默认 不必填（false） 必填（true）
     */
    boolean isNull() default false;

    /**
     * 数据类型
     */
    ParamType type() default ParamType.String;

    /**
     * 示例
     */
    String demo() default "";

    /**
     * 说明
     */
    String description() default "";
}
