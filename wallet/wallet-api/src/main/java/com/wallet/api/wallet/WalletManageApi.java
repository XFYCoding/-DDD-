package com.wallet.api.wallet;

import com.wallet.api.wallet.model.dto.request.FeeWalletBalanceRequest;
import com.wallet.api.wallet.model.dto.request.RefundWalletBalanceRequest;
import com.wallet.untils.response.CommonResponse;

/**
 * @author 小肥瑜
 */
public interface WalletManageApi {

    CommonResponse fee(FeeWalletBalanceRequest request);

    CommonResponse refund(RefundWalletBalanceRequest request);
}
