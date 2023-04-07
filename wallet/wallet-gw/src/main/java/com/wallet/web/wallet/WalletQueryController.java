package com.wallet.web.wallet;

import com.wallet.api.wallet.WalletQueryApi;
import com.wallet.api.wallet.model.dto.request.GetUserWalletBalanceRequest;
import com.wallet.api.wallet.model.dto.request.GetWalletFeeDetailsRequest;
import com.wallet.api.wallet.model.dto.result.UserWalletBalanceResult;
import com.wallet.api.wallet.model.dto.result.WalletFeeDetailsResult;
import com.wallet.untils.response.ListResponse;
import com.wallet.untils.response.SingleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 小肥瑜
 */
@RestController
@RequestMapping("/wallet/query")
public class WalletQueryController {

    private final WalletQueryApi walletQueryApi;

    public WalletQueryController(WalletQueryApi walletQueryApi) {
        this.walletQueryApi = walletQueryApi;
    }

    @GetMapping("/fee/detail")
    public ListResponse<WalletFeeDetailsResult> feeDetails(GetWalletFeeDetailsRequest request) {
        return walletQueryApi.feeDetails(request);
    }

    @GetMapping("/fee/detail")
    public SingleResponse<UserWalletBalanceResult> userWalletBalance(GetUserWalletBalanceRequest request) {
        return walletQueryApi.userWalletBalance(request);
    }


}
