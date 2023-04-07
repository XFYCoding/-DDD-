package com.wallet.web.wallet;

import com.wallet.api.wallet.WalletManageApi;
import com.wallet.api.wallet.model.dto.request.FeeWalletBalanceRequest;
import com.wallet.api.wallet.model.dto.request.RefundWalletBalanceRequest;
import com.wallet.untils.response.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 小肥瑜
 */
@RestController
@RequestMapping("/wallet/manager")
public class WalletManageController {

    private final WalletManageApi walletManageApi;

    public WalletManageController(WalletManageApi walletManageApi) {
        this.walletManageApi = walletManageApi;
    }

    @GetMapping("/fee/detail")
    public CommonResponse fee(FeeWalletBalanceRequest request) {
        return walletManageApi.fee(request);
    }

    @GetMapping("/fee/detail")
    public CommonResponse refund(RefundWalletBalanceRequest request) {
        return walletManageApi.refund(request);
    }
}
