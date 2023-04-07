package com.wallet.mapper;

import com.wallet.pojo.WalletDetailPo;
import com.wallet.wallet.model.WalletDetail;

import java.util.List;

/**
 * @author 小肥瑜
 */
public interface WalletDetailMapper extends CrudMapper<Long, WalletDetail, WalletDetailPo> {
    List<WalletDetailPo> selectByUserNumber(String userNumber);
}
