package io.github.yunbamboos.rest.api;

import io.github.yunbamboos.rest.api.swagger.GenerateSwagger;

public class GenerateSwaggerBuilder {

    private String file;

    private Class<?> primaryClass;

    public GenerateSwaggerBuilder file(String file) {
        this.file = file;
        return this;
    }

    public GenerateSwaggerBuilder primaryClass(Class<?> primaryClass) {
        this.primaryClass = primaryClass;
        return this;
    }

    public GenerateSwagger build() {
        GenerateSwagger swagger = new GenerateSwagger();
        swagger.file = file;
        swagger.primaryClass = primaryClass;
        return swagger;
    }
}
