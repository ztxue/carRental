package com.shengyi.common;


//返回类状态码
public enum ResultCode {
    SUCCESS("000000", "执行成功"),//成功
    FAIL("000400", "失败"),//失败
    UNAUTHORIZED("000401", "未认证（签名错误）"),//未认证（签名错误）
    NOT_FOUND("000404", "接口不存在"),//接口不存在
    INTERNAL_SERVER_ERROR("000500", "服务器内部错误");//服务器内部错误

    private final String code;
    private final String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
