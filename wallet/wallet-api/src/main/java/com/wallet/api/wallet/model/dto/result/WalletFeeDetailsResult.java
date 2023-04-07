package com.wallet.api.wallet.model.dto.result;

import com.wallet.wallet.model.WalletType;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author 小肥瑜
 */
public class WalletFeeDetailsResult implements Serializable {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 余额类型（消费，退款）
     */
    private WalletType walletType;
    /**
     * 余额
     */
    private BigDecimal balance;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public WalletType getWalletType() {
        return walletType;
    }

    public void setWalletType(WalletType walletType) {
        this.walletType = walletType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
