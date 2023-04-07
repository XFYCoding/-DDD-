package com.wallet.wallet.repo;

import com.wallet.wallet.model.Wallet;

/**
 * @author 小肥瑜
 */
public interface WalletRepo {
    Wallet findByUserNumber(String userNumber);

    void batchSave(Wallet wallet);
}
