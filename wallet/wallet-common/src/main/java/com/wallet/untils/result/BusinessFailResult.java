package com.wallet.untils.result;

/**
 *
 * @author 小肥瑜
 */
public interface BusinessFailResult extends Result {
    String getCodeStr();

    @Override
    default int getCode() {
        return CodeMsgEnum.BUSINESS_FAILED.getCode();
    }

    @Override
    default BusinessFailResult withMsg(String customMsg) {
        return DefaultBusinessFailResult.build(getCodeStr(), customMsg);
    }
}
