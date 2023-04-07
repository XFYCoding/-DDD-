package com.wallet.wallet.service;

import com.wallet.api.wallet.model.dto.request.FeeWalletBalanceRequest;
import com.wallet.api.wallet.model.dto.request.RefundWalletBalanceRequest;

/**
 * @author 小肥瑜
 */
public interface WalletManageService {

    void fee(FeeWalletBalanceRequest request);

    void refund(RefundWalletBalanceRequest request);
}
