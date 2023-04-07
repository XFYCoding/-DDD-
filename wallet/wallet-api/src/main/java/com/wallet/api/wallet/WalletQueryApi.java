package com.wallet.api.wallet;

import com.wallet.api.wallet.model.dto.request.GetUserWalletBalanceRequest;
import com.wallet.api.wallet.model.dto.request.GetWalletFeeDetailsRequest;
import com.wallet.api.wallet.model.dto.result.UserWalletBalanceResult;
import com.wallet.api.wallet.model.dto.result.WalletFeeDetailsResult;
import com.wallet.untils.response.ListResponse;
import com.wallet.untils.response.SingleResponse;

/**
 * @author 小肥瑜
 */
public interface WalletQueryApi {
    ListResponse<WalletFeeDetailsResult> feeDetails(GetWalletFeeDetailsRequest request);

    SingleResponse<UserWalletBalanceResult> userWalletBalance(GetUserWalletBalanceRequest request);
}
