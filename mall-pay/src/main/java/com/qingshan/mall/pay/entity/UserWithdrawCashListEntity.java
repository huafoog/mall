package com.qingshan.mall.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 提现记录表
 * 
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:17:46
 */
@Data
@TableName("user_withdraw_cash_list")
@ApiModel("提现记录表")
public class UserWithdrawCashListEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.ID_WORKER)
	@ApiModelProperty("id")
	private String id;
	/**
	 * 申请用户uuid
	 */
	@ApiModelProperty("申请用户uuid")
	private String userId;
	/**
	 * 提现（渠道）方式 1银行转账
	 */
	@ApiModelProperty("提现（渠道）方式 1银行转账")
	private Integer withdrawWay;
	/**
	 * 处理状态。 1发起申请（待审核理）前台显示处理中，2提现成功，3审核不通过
	 */
	@ApiModelProperty("处理状态。 1发起申请（待审核理）前台显示处理中，2提现成功，3审核不通过")
	private Integer withdrawStatus;
	/**
	 * 提现单号
	 */
	@ApiModelProperty("提现单号")
	private String number;
	/**
	 * 收款账户
	 */
	@ApiModelProperty("收款账户")
	private String receivableAccount;
	/**
	 * 收款人姓名
	 */
	@ApiModelProperty("收款人姓名")
	private String name;
	/**
	 * 开户行地址
	 */
	@ApiModelProperty("开户行地址")
	private String address;
	/**
	 * 提现金额
	 */
	@ApiModelProperty("提现金额")
	private BigDecimal withdrawFee;
	/**
	 * 审核不通过原因
	 */
	@ApiModelProperty("审核不通过原因")
	private String content;
	/**
	 * 审核人
	 */
	@ApiModelProperty("审核人")
	private String verifyUser;
	/**
	 * 操作人
	 */
	@ApiModelProperty("操作人")
	private String actionUser;
	/**
	 * 审核时间
	 */
	@ApiModelProperty("审核时间")
	private Date actionAt;
	/**
	 * 发送通知时间
	 */
	@ApiModelProperty("发送通知时间")
	private Date sentNoticeAt;
	/**
	 * 
	 */
	@ApiModelProperty("")
	private Date updateTime;
	/**
	 * 
	 */
	@ApiModelProperty("")
	private Date createTime;
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
