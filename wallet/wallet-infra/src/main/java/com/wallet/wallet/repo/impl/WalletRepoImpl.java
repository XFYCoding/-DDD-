package com.wallet.wallet.repo.impl;

import com.wallet.mapper.CrudMapper;
import com.wallet.mapper.WalletDetailMapper;
import com.wallet.mapper.WalletMapper;
import com.wallet.pojo.WalletDetailPo;
import com.wallet.pojo.WalletPo;
import com.wallet.untils.AssertUtils;
import com.wallet.untils.Lazy.Lazy;
import com.wallet.untils.ddd.BaseEntity;
import com.wallet.untils.ddd.BasePersistentEntity;
import com.wallet.untils.ddd.BasePojoSaveOperation;
import com.wallet.untils.exception.MyException;
import com.wallet.untils.result.BusinessResultEnum;
import com.wallet.wallet.model.Wallet;
import com.wallet.wallet.model.WalletDetail;
import com.wallet.wallet.model.WalletType;
import com.wallet.wallet.repo.WalletRepo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 小肥瑜
 */
@Service
public class WalletRepoImpl implements WalletRepo {
    private final WalletMapper walletMapper;
    private final WalletDetailMapper walletDetailMapper;
    private final RedissonClient redissonClient;

    public WalletRepoImpl(WalletMapper walletMapper,
                          WalletDetailMapper walletDetailMapper,
                          RedissonClient redissonClient) {
        this.walletMapper = walletMapper;
        this.walletDetailMapper = walletDetailMapper;
        this.redissonClient = redissonClient;
    }

    @Override
    public Wallet findByUserNumber(String userNumber) {
        WalletPo walletPo = walletMapper.selectByUserNumber(userNumber);
        //懒加载
        Lazy<List<WalletDetail>> walletDetail = Lazy.build(() -> {
            List<WalletDetailPo> walletDetailPos = walletDetailMapper.selectByUserNumber(userNumber);
            return ListUtils.emptyIfNull(walletDetailPos).stream().map(x -> new WalletDetail(x, x.getUserNumber(), x.getOrderId(), WalletType.findByCode(x.getWalletType()), x.getBalance())).collect(Collectors.toList());
        });
        return new Wallet(walletPo, walletPo.getBalance(), walletDetail);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void batchSave(Wallet wallet) {
        WalletPo.SaveOperation walletPoSaveOperation = new WalletPo.SaveOperation();
        fillSaveOperation(walletPoSaveOperation, Collections.singletonList(wallet), this::convert2WalletPo);

        WalletDetailPo.SaveOperation walletDetailPoSaveOperation = new WalletDetailPo.SaveOperation();
        fillSaveOperation(walletDetailPoSaveOperation, wallet.getWalletDetails(), this::convert2WalletDetailPo);

        RLock lock = redissonClient.getLock("WALLET" + wallet.getId());
        try {
            //加锁
            boolean res = lock.tryLock(1, 10, TimeUnit.SECONDS);
            if (!res) {
                throw new MyException("重复提交");
            }
            //插入数据库
            handle(walletPoSaveOperation, walletMapper);
            handle(walletDetailPoSaveOperation, walletDetailMapper);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }

    }


    public static <ID,
            E extends BaseEntity<ID>,
            POJO extends BasePersistentEntity<ID, E>,
            M extends CrudMapper<ID, E, POJO>> void handle(BasePojoSaveOperation<ID, POJO> saveOperation,
                                                           M baseMapper) {
        int hit;
        if (CollectionUtils.isNotEmpty(saveOperation.getNewPos())) {
            hit = baseMapper.batchInsert(saveOperation.getNewPos());
            AssertUtils.equals(saveOperation.getNewPos().size(), hit, BusinessResultEnum.DATA_STATUS_CHANGED);
        }
        if (CollectionUtils.isNotEmpty(saveOperation.getChangedPos())) {
            for (POJO toUpdate : saveOperation.getChangedPos()) {
                final Long preVersion = saveOperation.getIdWithPreVersion().get(toUpdate.getId());
                hit = baseMapper.updateById(toUpdate, preVersion);
                boolean success = hit == 1;
                AssertUtils.isTrue(success, BusinessResultEnum.DATA_STATUS_CHANGED);
            }
        }
        if (CollectionUtils.isNotEmpty(saveOperation.getToRemoveIds())) {
            baseMapper.updateEnabledByIds(saveOperation.getToRemoveIds(), false, new Date(), saveOperation.getUpdateUser());
        }
    }


    private WalletDetailPo convert2WalletDetailPo(WalletDetail walletDetail) {
        WalletDetailPo walletDetailPo = new WalletDetailPo();
        walletDetailPo.fillEntityMeta(walletDetail);
        walletDetailPo.setUserNumber(walletDetail.getUserNumber());
        walletDetailPo.setOrderId(walletDetail.getOrderId());
        walletDetailPo.setWalletType(walletDetail.getWalletType().getCode());
        walletDetailPo.setBalance(walletDetail.getBalance());
        return walletDetailPo;
    }

    private WalletPo convert2WalletPo(Wallet wallet) {
        WalletPo po = new WalletPo();
        po.fillEntityMeta(wallet);
        po.setBalance(wallet.getBalance());
        return po;
    }


    public final <ID,
            E extends BaseEntity<ID>,
            P extends BasePersistentEntity<ID, E>,
            OP extends BasePojoSaveOperation<ID, P>> void fillSaveOperation(OP saveOperation,
                                                                            List<E> entities,
                                                                            Function<E, P> entityConverter) {
        entities.forEach(entity -> {
            saveOperation.getIdWithPreVersion().put(entity.getId(), entity.previousVersion());
            if (!entity.isPersisted()) {
                saveOperation.getNewPos().add(entityConverter.apply(entity));
            } else if (entity.isChanged()) {
                saveOperation.getChangedPos().add(entityConverter.apply(entity));
            }
        });
    }
}
