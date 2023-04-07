package com.wallet.wallet.service.impl;

import com.wallet.api.wallet.model.dto.request.FeeWalletBalanceRequest;
import com.wallet.api.wallet.model.dto.request.RefundWalletBalanceRequest;
import com.wallet.untils.AssertUtils;
import com.wallet.untils.exception.MyException;
import com.wallet.wallet.model.Wallet;
import com.wallet.wallet.repo.WalletRepo;
import com.wallet.wallet.service.WalletManageService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 小肥瑜
 */
@Service
public class WalletManageServiceImpl implements WalletManageService {
    private final WalletRepo walletRepo;
    private final RedissonClient redissonClient;

    public WalletManageServiceImpl(WalletRepo walletRepo, RedissonClient redissonClient) {
        this.walletRepo = walletRepo;
        this.redissonClient = redissonClient;
    }

    @Override
    public void fee(FeeWalletBalanceRequest request) {
        AssertUtils.verify(request);
        Wallet wallet = walletRepo.findByUserNumber(request.getUserNumber());
        //消费操作
        wallet.fee(request.getBalance(),request.getOrderId());
        lock(wallet,"wallet_fee");
    }

    @Override
    public void refund(RefundWalletBalanceRequest request) {
        AssertUtils.verify(request);
        Wallet wallet = walletRepo.findByUserNumber(request.getUserNumber());
        //退款操作
        wallet.refund(request.getBalance(),request.getOrderId());
        lock(wallet,"wallet_refund");
    }

    private void lock(Wallet wallet,String name) {
        RLock lock = redissonClient.getLock(name + wallet.getId());
        try {
            //加锁
            boolean res = lock.tryLock(1, 10, TimeUnit.SECONDS);
            if (!res) {
                throw new MyException("重复提交");
            }
            walletRepo.batchSave(wallet);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
