package com.wallet.untils.result;


public interface Result {
    int getCode();
    String getMsg();

    default Result withMsg(String customMsg) {
        return new DefaultResult(getCode(), customMsg);
    }
}
