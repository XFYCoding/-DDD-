package com.wallet.wallet.model;

import com.wallet.untils.ddd.BaseEntity;
import com.wallet.untils.ddd.EntityMeta;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 小肥瑜
 */
public class WalletDetail extends BaseEntity<Long> implements Serializable {
    /**
     * 订单id
     */
    private final String userNumber;
    /**
     * 订单id
     */
    private final String orderId;
    /**
     * 余额类型（消费，退款）
     */
    private final WalletType walletType;
    /**
     * 余额(分为单位)
     */
    private final BigDecimal balance;

    public WalletDetail(Long id,
                        String createUser,
                        String userNumber,
                        String orderId,
                        WalletType walletType,
                        BigDecimal balance) {
        super(id, createUser);
        this.userNumber = userNumber;
        this.orderId = orderId;
        this.walletType = walletType;
        this.balance = balance;
    }

    public WalletDetail(EntityMeta<Long> entityMeta,
                        String userNumber,
                        String orderId,
                        WalletType walletType,
                        BigDecimal balance) {
        super(entityMeta);
        this.userNumber = userNumber;
        this.orderId = orderId;
        this.walletType = walletType;
        this.balance = balance;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public WalletType getWalletType() {
        return walletType;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
