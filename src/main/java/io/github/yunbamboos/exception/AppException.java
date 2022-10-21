package io.github.yunbamboos.exception;

import java.text.MessageFormat;

/**
 * 自定义异常类
 */
public class AppException extends RuntimeException {

    public static void error(String pattern, Object ... arguments){
        throw new AppException(MessageFormat.format(pattern,arguments));
    }

    /**
     * 程序异常编码
     */
    private final int code;
    /**
     * 程序异常内容
     */
    private final String msg;

    /**
     * 程序异常{@code msg}
     * @param msg 程序异常内容
     */
    public AppException(String msg) {
        super(msg);
        this.code = 500;
        this.msg = msg;
    }

    /**
     * 封装程序异常{@code e}
     * @param e 程序异常
     */
    public AppException(Throwable e) {
        super(e);
        this.code = 9999;
        this.msg = "未知异常";
    }

    /**
     * 封装程序异常枚举{@link ErrorCode}
     * @param errorCode {@link ErrorCode}
     */
    public AppException(ErrorCode errorCode) {
        super("code:" + errorCode.getCode() + ",msg:" + errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    /**
     * 程序异常编码
     *
     * @return 程序异常编码
     */
    public int getCode() {
        return code;
    }

    /**
     * 程序异常内容
     *
     * @return 程序异常内容
     */
    public String getMsg() {
        return msg;
    }
}
