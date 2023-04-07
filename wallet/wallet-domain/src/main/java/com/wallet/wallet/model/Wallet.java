package com.wallet.wallet.model;

import com.wallet.untils.Lazy.Lazy;
import com.wallet.untils.ddd.Aggregate;
import com.wallet.untils.ddd.BaseEntity;
import com.wallet.untils.ddd.EntityMeta;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 小肥瑜
 */
public class Wallet extends BaseEntity<String> implements Aggregate<String, Wallet> {
    /**
     * 余额(分为单位)
     */
    private BigDecimal balance;

    /**
     * 账单（懒加载）
     */
    private final Lazy<List<WalletDetail>> walletDetails;

    public Wallet(String userName,
                  String createUser,
                  BigDecimal balance,
                  Lazy<List<WalletDetail>> walletDetails) {
        super(userName, createUser);
        this.balance = balance;
        this.walletDetails = walletDetails;
    }

    public Wallet(EntityMeta<String> entityMeta,
                  BigDecimal balance,
                  Lazy<List<WalletDetail>> walletDetails) {
        super(entityMeta);
        this.balance = balance;
        this.walletDetails = walletDetails;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Lazy<List<WalletDetail>> getLazyWalletDetails() {
        return walletDetails;
    }

    public List<WalletDetail> getWalletDetails() {
        return walletDetails.get();
    }

    public void fee(BigDecimal balance, String orderId) {
        this.balance = this.balance.subtract(balance);
        fillDetail(balance, orderId,WalletType.Fee);
        this.onUpdate(this.id);
    }

    public void refund(BigDecimal balance, String orderId) {
        this.balance = this.balance.add(balance);
        fillDetail(balance, orderId,WalletType.ReFund);
        this.onUpdate(this.id);
    }

    private void fillDetail(BigDecimal balance, String orderId,WalletType walletType) {
        List<WalletDetail> walletDetails = getWalletDetails();
        //这里的id自增可以用雪花算法，数据库自定义，数据库自增来实现,redis自增
        walletDetails.add(new WalletDetail(null,id,id, orderId, walletType, balance));
    }

}
