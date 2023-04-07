package com.wallet.api.wallet.model.dto.request;

import com.wallet.untils.AssertUtils;
import com.wallet.untils.SelfCheckRequest;
import com.wallet.untils.result.BusinessResultEnum;

import java.math.BigDecimal;


/**
 * @author 小肥瑜
 */
public class RefundWalletBalanceRequest implements SelfCheckRequest {
    private String userNumber;
    private BigDecimal balance;
    private String orderId;

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void verifyAndThrow(boolean trimValue) {
        AssertUtils.notBlank(userNumber, BusinessResultEnum.MISSING_PARAM.withMsgParams("userNumber"));
        AssertUtils.notNull(balance, BusinessResultEnum.MISSING_PARAM.withMsgParams("balance"));
        AssertUtils.notBlank(orderId, BusinessResultEnum.MISSING_PARAM.withMsgParams("orderId"));
        AssertUtils.isTrue(balance.compareTo(BigDecimal.ZERO) >= 0, BusinessResultEnum.MISSING_PARAM.withMsgParams("balance"));
    }
}
