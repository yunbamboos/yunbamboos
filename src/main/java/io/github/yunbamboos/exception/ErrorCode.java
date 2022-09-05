package io.github.yunbamboos.exception;
/**
 * 异常枚举
 * */
public enum ErrorCode {

    /**认证失败*/
    E401(401, "认证失败"),
    /**服务器拒绝访问*/
    E403(403, "服务器拒绝访问"),
    /**未找到服务*/
    E404(404, "未找到服务"),
    /**请求方法不允许*/
    E405(405, "请求方法不允许"),
    /**服务器端执行请求时发生了错误*/
    E500(500, "服务器端执行请求时发生了错误"),
    /**无法处理请求*/
    E503(503, "无法处理请求");

    private final int code;
    private final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回编码
     * @return 返回编码
     * */
    public int getCode() {
        return code;
    }

    /**
     * 返回内容
     * @return 返回内容
     * */
    public String getMsg() {
        return msg;
    }

}
