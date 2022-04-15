package com.qingshan.mall.pay.mapper;

import com.qingshan.mall.pay.entity.WalletLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户钱包流水记录表
 * 
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-15 14:54:48
 */
@Mapper
public interface WalletLogMapper extends BaseMapper<WalletLogEntity> {
	
}
