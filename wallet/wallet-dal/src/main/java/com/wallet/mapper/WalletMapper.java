package com.wallet.mapper;

import com.wallet.pojo.WalletPo;
import com.wallet.wallet.model.Wallet;

/**
 * @author 小肥瑜
 */
public interface WalletMapper extends CrudMapper<String, Wallet,WalletPo> {
    WalletPo selectByUserNumber(String userNumber);
}
