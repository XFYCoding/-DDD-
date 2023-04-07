package com.wallet.wallet.service;

import com.wallet.api.wallet.model.dto.request.GetUserWalletBalanceRequest;
import com.wallet.api.wallet.model.dto.request.GetWalletFeeDetailsRequest;
import com.wallet.api.wallet.model.dto.result.UserWalletBalanceResult;
import com.wallet.api.wallet.model.dto.result.WalletFeeDetailsResult;

import java.util.List;

/**
 * @author 小肥瑜
 */
public interface WalletQueryService {
    List<WalletFeeDetailsResult> feeDetails(GetWalletFeeDetailsRequest request);

    UserWalletBalanceResult userWalletBalance(GetUserWalletBalanceRequest request);
}
