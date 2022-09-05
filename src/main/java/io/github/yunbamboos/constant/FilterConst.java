package io.github.yunbamboos.constant;
/**
 * 过滤器常量
 * */
public final class FilterConst {

    private FilterConst(){}

    /**
     * http请求方法过滤器{@see HttpRequestMethodFilter}
     * */
    public static final int HTTP_REQUEST_METHOD_ORDER = 1000;

    /**
     * 加载服务过滤器{@see LoadServiceFilter}
     * */
    public static final int LOAD_SERVICE_ORDER = 2000;

    /**
     * 认证服务过滤器{@see AuthenticationFilter}
     * */
    public static final int AUTHENTICATION_ORDER = 3000;

    /**
     * 读取request内容过滤器{@see ReadRequestFilter}
     * */
    public static final int READ_REQUEST_ORDER = 4000;

    /**
     * 参数转换InDTO过滤器{@see RequestToInDTOFilter}
     * */
    public static final int REQUEST_TO_IN_DTO_ORDER = 5000;

    /**
     * 调用RestService服务过滤器{@see InvokeRestServiceFilter}
     * */
    public static final int INVOKE_REST_SERVICE_ORDER = 6000;

    /**
     * OutDTO 转换 response过滤器 {@see OutDTOToResponseFilter}
     * */
    public static final int OUT_DTO_TO_RESPONSE_ORDER = 8000;

    /**
     * 写response过滤器 {@see WriteResponseFilter}
     * */
    public static final int WRITE_RESPONSE_ORDER = 9000;
}
