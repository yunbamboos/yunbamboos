package com.github.yunbamboos.rest.anno;

public enum ParamType {
    /**
     * 字符串
     */
    String("string"),
    /**
     * 整数
     */
    Integer("integer"),
    /**
     * 浮点数
     */
    Float("float"),

    Object("object"),
    ;

    private final String type;


    ParamType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
