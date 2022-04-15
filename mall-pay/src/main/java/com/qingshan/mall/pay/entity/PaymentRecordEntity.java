package com.qingshan.mall.pay.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 支付记录列表
 * 
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-15 14:54:47
 */
@Data
@TableName("payment_record")
public class PaymentRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *  id
	 */
	@TableId
	private String id;
	/**
	 * 用户uuid
	 */
	private String userId;
	/**
	 * 支付号
	 */
	private Long payNum;
	/**
	 * 第三方付款成功交易号
	 */
	private String tradeNo;
	/**
	 * 订单类型 1充值 
	 */
	private Integer orderType;
	/**
	 * 充值订单表uuid
	 */
	private String orderUuid;
	/**
	 * 付款方式 1微信 2支付宝 
	 */
	private Integer payWay;
	/**
	 * 支付状态 1 支付成功，2 支付失败 
	 */
	private Integer payStatus;
	/**
	 * 步骤 1 创建支付，2 支付回调通知
	 */
	private Integer step;
	/**
	 * 请求支付状态，1：成功，2：失败
	 */
	private Integer postStatus;
	/**
	 * 回调状态，1：成功，2：失败
	 */
	private Integer returnStatus;
	/**
	 * 支付价格
	 */
	private BigDecimal totalFee;
	/**
	 * 第三方返回错误码
	 */
	private String resultCode;
	/**
	 * 第三方返回的错误记录
	 */
	private String errCodeStr;
	/**
	 * 货币类型
	 */
	private String currency;
	/**
	 * ip2long
	 */
	private Integer ip2long;
	/**
	 * 提交post json 数据
	 */
	private String postJson;
	/**
	 * 回调post return data json
	 */
	private String returnJson;
	/**
	 * 创建时间
	 */
	private Date createAt;
	/**
	 * 更新时间
	 */
	private Date updateAt;
	/**
	 * 支付成功时间
	 */
	private Date successAt;
	/**
	 * 手续费比例，千位比。6/1000
	 */
	private Integer procedureKb;
	/**
	 * 支付渠道收取手续费金额
	 */
	private BigDecimal procedureFee;

}
