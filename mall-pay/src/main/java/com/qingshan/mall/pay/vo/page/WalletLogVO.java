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
 * 用户钱包流水记录表
 * 
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-04-18 17:06:43
 */
@Data
public class WalletLogVO extends BasePageInputVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty("id")
	private String id;
	/**
	 * 用户id
	 */
	@ApiModelProperty("用户id")
	private String userId;
	/**
	 * 流水号
	 */
	@ApiModelProperty("流水号")
	private String number;
	/**
	 * 业务类型，1：充值，2：提现  3：下单
	 */
	@ApiModelProperty("业务类型，1：充值，2：提现  3：下单")
	private Integer targetType;
	/**
	 * 来源uuid（如提现uuid）
	 */
	@ApiModelProperty("来源uuid（如提现uuid）")
	private String targetUuid;
	/**
	 * 操作类型，1：充值，2：提现，3：订单
	 */
	@ApiModelProperty("操作类型，1：充值，2：提现，3：订单")
	private Integer actionType;
	/**
	 * 变动的金额，正负数。
	 */
	@ApiModelProperty("变动的金额，正负数。")
	private BigDecimal fee;
	/**
	 * 
	 */
	@ApiModelProperty("")
	private String originalAccountJson;
	/**
	 * 
	 */
	@ApiModelProperty("")
	private String disposeAccountJson;
	/**
	 * 处理状态1，处理完成，0未完成
	 */
	@ApiModelProperty("处理状态1，处理完成，0未完成")
	private Integer status;
	/**
	 * 处理结果，0：没有变更，1：有变更。
	 */
	@ApiModelProperty("处理结果，0：没有变更，1：有变更。")
	private Integer resultType;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date createAt;

}
