package com.wallet.pojo;

import com.wallet.untils.ddd.BasePersistentEntity;
import com.wallet.untils.ddd.BasePojoSaveOperation;
import com.wallet.wallet.model.Wallet;

import java.math.BigDecimal;

/**
 * @author 小肥瑜
 */
public class WalletPo extends BasePersistentEntity<String, Wallet> {
    /**
     * 余额(分为单位)
     */
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static class SaveOperation extends BasePojoSaveOperation<String, WalletPo> {

    }
}
