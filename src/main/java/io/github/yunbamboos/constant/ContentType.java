package io.github.yunbamboos.constant;

public enum ContentType {

    /**
     * JSON数据格式
     */
    APPLICATION_JSON("application/json");

    private final String content;

    ContentType(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
