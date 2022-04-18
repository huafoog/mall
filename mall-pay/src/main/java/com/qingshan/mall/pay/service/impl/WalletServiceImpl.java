package com.qingshan.mall.pay.service.impl;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qingshan.common.core.constant.enums.BizCodeEnum;
import com.qingshan.common.core.encrypt.RSASignature;
import com.qingshan.common.core.exception.BusinessException;
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

/**
 * 用户钱包
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@Service("walletService")
public class WalletServiceImpl extends ServiceImpl<WalletMapper, WalletEntity> implements WalletService {

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
    public int createWallet(String userId){

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
        return baseMapper.insert(wallet);
    }

}