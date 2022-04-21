package com.qingshan.mall.pay.service.impl;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qingshan.common.core.constant.enums.BizCodeEnum;
import com.qingshan.common.core.encrypt.RSASignature;
import com.qingshan.common.core.exception.BusinessException;
import com.qingshan.mall.pay.entity.WalletLogEntity;
import com.qingshan.mall.pay.service.WalletLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.Query;

import com.qingshan.mall.pay.mapper.WalletMapper;
import com.qingshan.mall.pay.entity.WalletEntity;
import com.qingshan.mall.pay.service.WalletService;
import com.qingshan.mall.pay.vo.page.WalletVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户钱包
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@Service("walletService")
@AllArgsConstructor
public class WalletServiceImpl extends ServiceImpl<WalletMapper, WalletEntity> implements WalletService {


    private final WalletLogService walletLogService;

    @Override
    public PageUtils queryPage(WalletVO params) {
        IPage<WalletEntity> page = this.page(
                new Query<WalletEntity>().getPage(params),
                new QueryWrapper<WalletEntity>()
        );

        return new PageUtils(page);
    }


    /**
     * 用户创建钱包
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createWallet(String userId){
        int i = 0;
        Integer integer = baseMapper.selectCount(Wrappers.<WalletEntity>lambdaQuery().eq(WalletEntity::getUserId, userId));
        if (integer > 0){
            throw new BusinessException(BizCodeEnum.USER_WALLET_EXIST);
        }

        WalletEntity wallet = new WalletEntity();
        wallet.setUserId(userId);
        wallet.setWalletIncome(new BigDecimal("0"));
        wallet.setWalletOutcome(new BigDecimal("0"));
        wallet.setBalanceFee(new BigDecimal("0"));
        String sign = RSASignature.sign(wallet, "");
        wallet.setSign(sign);
        i += baseMapper.insert(wallet);

        WalletLogEntity walletLogEntity = new WalletLogEntity();
        walletLogEntity.setUserId(userId);
        walletLogEntity.setNumber(IdWorker.getTimeId());
        walletLogEntity.setTargetType(1);
        walletLogEntity.setTargetUuid(userId);
        walletLogEntity.setActionType(1);
        walletLogEntity.setFee(new BigDecimal("0"));
        walletLogEntity.setOriginalAccountJson("");
        walletLogEntity.setDisposeAccountJson("");
        walletLogEntity.setStatus(0);
        walletLogEntity.setResultType(0);
        walletLogService.save(walletLogEntity);
        return i;
    }

}