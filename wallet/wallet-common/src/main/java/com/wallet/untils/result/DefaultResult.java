package com.wallet.untils.result;


/**
 * @author 小肥瑜
 */
public class DefaultResult implements Result {
    private int code;
    private String msg;

    public DefaultResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
