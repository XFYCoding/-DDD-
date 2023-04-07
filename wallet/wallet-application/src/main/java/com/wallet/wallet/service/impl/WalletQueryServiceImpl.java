package com.wallet.wallet.service.impl;

import com.wallet.api.wallet.model.dto.request.GetUserWalletBalanceRequest;
import com.wallet.api.wallet.model.dto.request.GetWalletFeeDetailsRequest;
import com.wallet.api.wallet.model.dto.result.UserWalletBalanceResult;
import com.wallet.api.wallet.model.dto.result.WalletFeeDetailsResult;
import com.wallet.untils.AssertUtils;
import com.wallet.wallet.model.Wallet;
import com.wallet.wallet.repo.WalletRepo;
import com.wallet.wallet.service.WalletQueryService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 小肥瑜
 */
@Service
public class WalletQueryServiceImpl implements WalletQueryService {
    private final WalletRepo walletRepo;

    public WalletQueryServiceImpl(WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
    }

    @Override
    public List<WalletFeeDetailsResult> feeDetails(GetWalletFeeDetailsRequest request) {
        AssertUtils.verify(request);
        Wallet wallet = walletRepo.findByUserNumber(request.getUserNumber());
        return ListUtils.emptyIfNull(wallet.getWalletDetails()).stream().map(x -> {
            WalletFeeDetailsResult result = new WalletFeeDetailsResult();
            result.setOrderId(x.getOrderId());
            result.setWalletType(x.getWalletType());
            result.setBalance(x.getBalance());
            return result;
        }).collect(Collectors.toList());
    }

    @Override
    public UserWalletBalanceResult userWalletBalance(GetUserWalletBalanceRequest request) {
        Wallet wallet = walletRepo.findByUserNumber(request.getUserNumber());
        return UserWalletBalanceResult.build(wallet.getBalance());
    }
}
