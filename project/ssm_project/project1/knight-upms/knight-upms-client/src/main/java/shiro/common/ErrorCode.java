package shiro.common;

public class ErrorCode {
    public static final int AUTH_ERROR = 401;//没有提供认证信息，请求的时候没有带上token等
    public static final int NOT_FOUND = 404;//请求的资源部存在

}
