package com.qingshan.mall.pay.vo.page;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qingshan.common.core.vo.BasePageInputVO;
import io.swagger.annotations.*;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 充值订单
 * 
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@Data
public class UserTopUpOrderVO extends BasePageInputVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty("id")
	private String id;
	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	private String userId;
	/**
	 * 订单号
	 */
	@ApiModelProperty("订单号")
	private String orderNum;
	/**
	 * 订单总金额
	 */
	@ApiModelProperty("订单总金额")
	private BigDecimal amounts;
	/**
	 * 货币类型
	 */
	@ApiModelProperty("货币类型")
	private String currency;
	/**
	 * 实际支付金额
	 */
	@ApiModelProperty("实际支付金额")
	private BigDecimal payFee;
	/**
	 * 汇率
	 */
	@ApiModelProperty("汇率")
	private BigDecimal exchange;
	/**
	 * 支付状态：0待支付，1已支付，100已取消
	 */
	@ApiModelProperty("支付状态：0待支付，1已支付，100已取消")
	private Integer status;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private Date updateAt;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date createAt;
	/**
	 * 0未删除1已删除
	 */
	@ApiModelProperty("0未删除1已删除")
	private Integer deleted;

}
