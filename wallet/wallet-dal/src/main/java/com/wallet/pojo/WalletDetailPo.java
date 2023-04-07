package com.wallet.pojo;

import com.wallet.untils.ddd.BasePersistentEntity;
import com.wallet.untils.ddd.BasePojoSaveOperation;
import com.wallet.wallet.model.WalletDetail;
import com.wallet.wallet.model.WalletType;

import java.math.BigDecimal;

/**
 * @author 小肥瑜
 */
public class WalletDetailPo extends BasePersistentEntity<Long, WalletDetail> {
    /**
     * 订单id
     */
    private String userNumber;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 余额类型（消费，退款）
     */
    private String walletType;
    /**
     * 余额(分为单位)
     */
    private BigDecimal balance;

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWalletType() {
        return walletType;
    }

    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static class SaveOperation extends BasePojoSaveOperation<Long, WalletDetailPo> {

    }
}
