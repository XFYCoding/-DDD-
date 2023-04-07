package com.wallet.untils.exception;

import com.wallet.untils.result.BusinessFailResult;
import com.wallet.untils.result.CodeMsgEnum;

/**
 * @author 小肥瑜
 */
public class BusinessException extends MyException {

    private static final long serialVersionUID = 2896441637073637009L;

    private final BusinessFailResult businessFailResult;
    private final String businessCode;

    public BusinessException(BusinessFailResult businessFailResult) {
        super(CodeMsgEnum.BUSINESS_FAILED.getCode(), businessFailResult.getMsg());
        this.businessCode = businessFailResult.getCodeStr();
        this.businessFailResult = businessFailResult;
    }

    public BusinessException(BusinessFailResult businessFailResult, Throwable cause) {
        super(CodeMsgEnum.BUSINESS_FAILED.getCode(), businessFailResult.getMsg(), cause);
        this.businessCode = businessFailResult.getCodeStr();
        this.businessFailResult = businessFailResult;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public BusinessFailResult getBusinessFailResult() {
        return businessFailResult;
    }
}
