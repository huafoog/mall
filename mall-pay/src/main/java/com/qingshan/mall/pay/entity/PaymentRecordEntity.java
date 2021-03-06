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
 * 支付记录列表
 * 
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:17:46
 */
@Data
@TableName("payment_record")
@ApiModel("支付记录列表")
public class PaymentRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *  id
	 */
	@TableId(type = IdType.ID_WORKER)
	@ApiModelProperty(" id")
	private String id;
	/**
	 * 用户uuid
	 */
	@ApiModelProperty("用户uuid")
	private String userId;
	/**
	 * 支付号
	 */
	@ApiModelProperty("支付号")
	private Long payNum;
	/**
	 * 第三方付款成功交易号
	 */
	@ApiModelProperty("第三方付款成功交易号")
	private String tradeNo;
	/**
	 * 订单类型 1充值 
	 */
	@ApiModelProperty("订单类型 1充值 ")
	private Integer orderType;
	/**
	 * 充值订单表uuid
	 */
	@ApiModelProperty("充值订单表uuid")
	private String orderUuid;
	/**
	 * 付款方式 1微信 2支付宝 
	 */
	@ApiModelProperty("付款方式 1微信 2支付宝 ")
	private Integer payWay;
	/**
	 * 支付状态 1 支付成功，2 支付失败 
	 */
	@ApiModelProperty("支付状态 1 支付成功，2 支付失败 ")
	private Integer payStatus;
	/**
	 * 步骤 1 创建支付，2 支付回调通知
	 */
	@ApiModelProperty("步骤 1 创建支付，2 支付回调通知")
	private Integer step;
	/**
	 * 请求支付状态，1：成功，2：失败
	 */
	@ApiModelProperty("请求支付状态，1：成功，2：失败")
	private Integer postStatus;
	/**
	 * 回调状态，1：成功，2：失败
	 */
	@ApiModelProperty("回调状态，1：成功，2：失败")
	private Integer returnStatus;
	/**
	 * 支付价格
	 */
	@ApiModelProperty("支付价格")
	private BigDecimal totalFee;
	/**
	 * 第三方返回错误码
	 */
	@ApiModelProperty("第三方返回错误码")
	private String resultCode;
	/**
	 * 第三方返回的错误记录
	 */
	@ApiModelProperty("第三方返回的错误记录")
	private String errCodeStr;
	/**
	 * 货币类型
	 */
	@ApiModelProperty("货币类型")
	private String currency;
	/**
	 * ip2long
	 */
	@ApiModelProperty("ip2long")
	private Integer ip2long;
	/**
	 * 提交post json 数据
	 */
	@ApiModelProperty("提交post json 数据")
	private String postJson;
	/**
	 * 回调post return data json
	 */
	@ApiModelProperty("回调post return data json")
	private String returnJson;
	/**
	 * 支付成功时间
	 */
	@ApiModelProperty("支付成功时间")
	private Date successAt;
	/**
	 * 手续费比例，千位比。6/1000
	 */
	@ApiModelProperty("手续费比例，千位比。6/1000")
	private Integer procedureKb;
	/**
	 * 支付渠道收取手续费金额
	 */
	@ApiModelProperty("支付渠道收取手续费金额")
	private BigDecimal procedureFee;
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
