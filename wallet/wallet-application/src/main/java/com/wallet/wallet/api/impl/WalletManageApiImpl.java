package com.wallet.wallet.api.impl;

import com.wallet.api.wallet.WalletManageApi;
import com.wallet.api.wallet.model.dto.request.FeeWalletBalanceRequest;
import com.wallet.api.wallet.model.dto.request.RefundWalletBalanceRequest;
import com.wallet.untils.response.CommonResponse;
import com.wallet.wallet.service.WalletManageService;
import org.springframework.stereotype.Service;

/**
 * @author 小肥瑜
 */
@Service
public class WalletManageApiImpl implements WalletManageApi {
    private final WalletManageService walletManageService;

    public WalletManageApiImpl(WalletManageService walletManageService) {
        this.walletManageService = walletManageService;
    }

    @Override
    public CommonResponse fee(FeeWalletBalanceRequest request) {
        walletManageService.fee(request);
        return CommonResponse.buildSuccess();
    }

    @Override
    public CommonResponse refund(RefundWalletBalanceRequest request) {
        walletManageService.refund(request);
        return CommonResponse.buildSuccess();
    }
}
