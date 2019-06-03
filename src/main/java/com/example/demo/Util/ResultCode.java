package com.example.demo.Util;
/**
 * 王国超 2019/06/02
 * 返回类对象
 */
public enum ResultCode {
    /**
     * 成功,200
     */
    SUCCESS(200),
    /**
     * 失败,400
     */
    FAIL(400),
    /**
     * 未认证,401
     */
    UNAUTHORIZED(401),
    /**
     * 接口不存在,404
     */
    NOT_FOUND(404),
    /**
     * 业务级异常,412
     */
    SERVICE_EXCEPTION(412),
    /**
     * 服务器内部异常,500
     */
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
