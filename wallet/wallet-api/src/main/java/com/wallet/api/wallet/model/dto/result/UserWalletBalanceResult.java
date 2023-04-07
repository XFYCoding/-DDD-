package com.wallet.api.wallet.model.dto.result;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author 小肥瑜
 */
public class UserWalletBalanceResult implements Serializable {
    private BigDecimal balance;

    public static UserWalletBalanceResult build(BigDecimal balance) {
        UserWalletBalanceResult result = new UserWalletBalanceResult();
        result.setBalance(balance);
        return result;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
