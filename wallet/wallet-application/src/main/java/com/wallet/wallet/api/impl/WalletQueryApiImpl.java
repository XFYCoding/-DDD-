package com.wallet.wallet.api.impl;

import com.wallet.api.wallet.WalletQueryApi;
import com.wallet.api.wallet.model.dto.request.GetUserWalletBalanceRequest;
import com.wallet.api.wallet.model.dto.request.GetWalletFeeDetailsRequest;
import com.wallet.api.wallet.model.dto.result.UserWalletBalanceResult;
import com.wallet.api.wallet.model.dto.result.WalletFeeDetailsResult;
import com.wallet.untils.response.ListResponse;
import com.wallet.untils.response.SingleResponse;
import com.wallet.wallet.service.WalletQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小肥瑜
 */
@Service
public class WalletQueryApiImpl implements WalletQueryApi {
    private final WalletQueryService walletQueryService;

    public WalletQueryApiImpl(WalletQueryService walletQueryService) {
        this.walletQueryService = walletQueryService;
    }

    @Override
    public ListResponse<WalletFeeDetailsResult> feeDetails(GetWalletFeeDetailsRequest request) {
        List<WalletFeeDetailsResult> result = walletQueryService.feeDetails(request);
        return ListResponse.buildSuccess(result);
    }

    @Override
    public SingleResponse<UserWalletBalanceResult> userWalletBalance(GetUserWalletBalanceRequest request) {
        UserWalletBalanceResult result = walletQueryService.userWalletBalance(request);
        return SingleResponse.buildSuccess(result);
    }
}
