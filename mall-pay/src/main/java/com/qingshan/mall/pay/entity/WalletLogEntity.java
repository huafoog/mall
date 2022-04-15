package com.qingshan.mall.pay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户钱包流水记录表
 * 
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-15 14:54:48
 */
@Data
@TableName("wallet_log")
public class WalletLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private String id;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 流水号
	 */
	private String number;
	/**
	 * 业务类型，1：充值，2：提现  3：下单
	 */
	private Integer targetType;
	/**
	 * 来源uuid（如提现uuid）
	 */
	private String targetUuid;
	/**
	 * 操作类型，1：充值，2：提现，3：订单
	 */
	private Integer actionType;
	/**
	 * 变动的金额，正负数。
	 */
	private BigDecimal fee;
	/**
	 * 
	 */
	private String originalAccountJson;
	/**
	 * 
	 */
	private String disposeAccountJson;
	/**
	 * 处理状态1，处理完成，0未完成
	 */
	private Integer status;
	/**
	 * 处理结果，0：没有变更，1：有变更。
	 */
	private Integer resultType;
	/**
	 * 创建时间
	 */
	private Date createAt;

}
