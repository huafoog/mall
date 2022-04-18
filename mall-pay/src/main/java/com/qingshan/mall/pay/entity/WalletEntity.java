package com.qingshan.mall.pay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户钱包
 * 
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:17:46
 */
@Data
@TableName("wallet")
@ApiModel("用户钱包")
public class WalletEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	@ApiModelProperty("用户id")
	private String userId;
	/**
	 * 钱包总收入额
	 */
	@ApiModelProperty("钱包总收入额")
	private BigDecimal walletIncome;
	/**
	 * 钱包总支出额
	 */
	@ApiModelProperty("钱包总支出额")
	private BigDecimal walletOutcome;
	/**
	 * 钱包总可用余额
	 */
	@ApiModelProperty("钱包总可用余额")
	private BigDecimal balanceFee;
	/**
	 * 用于安全检查，检查不通过为异常。
	 */
	@ApiModelProperty("用于安全检查，检查不通过为异常。")
	private String sign;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private Date createTime;
	/**
	 * 
	 */
	@ApiModelProperty("")
	private Date updateTime;
	/**
	 * 
	 */
	@ApiModelProperty("")
	private String createMan;
	/**
	 * 
	 */
	@ApiModelProperty("")
	private String updateMan;
	/**
	 * 
	 */
	@ApiModelProperty("")
	private Integer delFlag;

}
