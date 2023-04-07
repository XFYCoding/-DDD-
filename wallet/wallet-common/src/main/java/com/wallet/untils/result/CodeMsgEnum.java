package com.wallet.untils.result;

/**
 * @author 小肥瑜
 */

public enum CodeMsgEnum implements Result {

    SUCCESS(0, "接口响应成功!"),
    /**
     * 业务异常，查看具体的业务异常codeStr
     */
    BUSINESS_FAILED(1, "操作失败"),
    ERROR(-1, "接口发生失败!"),
    TIMEOUT(2, "操作超时，请重试"),
    FAILED(9999, "操作失败!"),
    WRONG_PARAMETER(1001, "请求参数错误"),
    ERROR_MISSING_PARAMETER(1002, "参数错误：请求消息缺少参数{#}!!"),
    ERROR_INVALID_FORMAT(1003, "参数错误：请求消息格式错误!"),
    FAILED_NO_FOUND_DATA(1004, "数据不存在!"),
    FAILED_EXITS(1005, "数据已存在!"),
    ERROR_USER_ID_NOT_EXIST(1006, "USER_ID不存在!"),
    ;

    int code;
    String msg;

    CodeMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(int code) {
        for (CodeMsgEnum codeMsg : CodeMsgEnum.values()) {
            if (codeMsg.code == code) {
                return codeMsg.msg;
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Result withMsg(String customMsg) {
        return Result.super.withMsg(customMsg);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}