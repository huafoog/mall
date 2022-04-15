package com.qingshan.mall.pay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户钱包
 * 
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-15 14:54:48
 */
@Data
@TableName("wallet")
public class WalletEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private String userId;
	/**
	 * 钱包总收入额
	 */
	private BigDecimal walletIncome;
	/**
	 * 钱包总支出额
	 */
	private BigDecimal walletOutcome;
	/**
	 * 钱包总可用余额
	 */
	private BigDecimal balanceFee;
	/**
	 * 用于安全检查，检查不通过为异常。
	 */
	private String checkSign;
	/**
	 * 更新时间
	 */
	private Date updateAt;

}
