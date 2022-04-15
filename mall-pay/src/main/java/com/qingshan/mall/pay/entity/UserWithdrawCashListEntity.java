package com.qingshan.mall.pay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 提现记录表
 * 
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-15 14:54:48
 */
@Data
@TableName("user_withdraw_cash_list")
public class UserWithdrawCashListEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private String id;
	/**
	 * 申请用户uuid
	 */
	private String userId;
	/**
	 * 提现（渠道）方式 1银行转账
	 */
	private Integer withdrawWay;
	/**
	 * 处理状态。 1发起申请（待审核理）前台显示处理中，2提现成功，3审核不通过
	 */
	private Integer withdrawStatus;
	/**
	 * 提现单号
	 */
	private String number;
	/**
	 * 收款账户
	 */
	private String receivableAccount;
	/**
	 * 收款人姓名
	 */
	private String name;
	/**
	 * 开户行地址
	 */
	private String address;
	/**
	 * 提现金额
	 */
	private BigDecimal withdrawFee;
	/**
	 * 审核不通过原因
	 */
	private String content;
	/**
	 * 审核人
	 */
	private String verifyUser;
	/**
	 * 操作人
	 */
	private String actionUser;
	/**
	 * 审核时间
	 */
	private Date actionAt;
	/**
	 * 发送通知时间
	 */
	private Date sentNoticeAt;
	/**
	 * 创建时间
	 */
	private Date createAt;
	/**
	 * 更新时间
	 */
	private Date updateAt;
	/**
	 * 是否删除：0未删除，1已删除
	 */
	private Integer deleted;

}
