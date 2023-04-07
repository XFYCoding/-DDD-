package com.wallet.wallet.service.impl;

import com.wallet.api.wallet.model.dto.request.FeeWalletBalanceRequest;
import com.wallet.api.wallet.model.dto.request.RefundWalletBalanceRequest;
import com.wallet.untils.AssertUtils;
import com.wallet.wallet.model.Wallet;
import com.wallet.wallet.repo.WalletRepo;
import com.wallet.wallet.service.WalletManageService;
import org.springframework.stereotype.Service;

/**
 * @author 小肥瑜
 */
@Service
public class WalletManageServiceImpl implements WalletManageService {
    private final WalletRepo walletRepo;

    public WalletManageServiceImpl(WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
    }

    @Override
    public void fee(FeeWalletBalanceRequest request) {
        AssertUtils.verify(request);
        Wallet wallet = walletRepo.findByUserNumber(request.getUserNumber());
        wallet.fee(request.getBalance());
        walletRepo.batchSave(wallet);
    }

    @Override
    public void refund(RefundWalletBalanceRequest request) {
        AssertUtils.verify(request);
        Wallet wallet = walletRepo.findByUserNumber(request.getUserNumber());
        wallet.refund(request.getBalance());
        walletRepo.batchSave(wallet);
    }
}
