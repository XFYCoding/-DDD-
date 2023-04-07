package com.wallet.api.wallet.model.dto.request;

import com.wallet.untils.AssertUtils;
import com.wallet.untils.SelfCheckRequest;
import com.wallet.untils.result.BusinessResultEnum;


/**
 * @author 小肥瑜
 */
public class GetUserWalletBalanceRequest implements SelfCheckRequest {
    private String userNumber;

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public void verify() {
        this.userNumber = userNumber;
    }

    @Override
    public void verifyAndThrow(boolean trimValue) {
        AssertUtils.notBlank(userNumber, BusinessResultEnum.MISSING_PARAM.withMsgParams("userNumber"));
    }
}
