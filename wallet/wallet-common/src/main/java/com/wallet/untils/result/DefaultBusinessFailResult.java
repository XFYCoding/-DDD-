package com.wallet.untils.result;

/**
 * @author 小肥瑜
 */
public class DefaultBusinessFailResult implements BusinessFailResult {

    private String codeStr;
    private String msg;

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCodeStr() {
        return codeStr;
    }

    public static DefaultBusinessFailResult build(String codeStr, String msg) {
        DefaultBusinessFailResult result = new DefaultBusinessFailResult();

        result.codeStr = codeStr;
        result.msg = msg;

        return result;
    }
}
