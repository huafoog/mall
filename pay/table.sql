DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet` (
  `user_id` varchar(36) NOT NULL COMMENT '用户id',
  `wallet_income` decimal(18,2) unsigned DEFAULT '0.00' COMMENT '钱包总收入额',
  `wallet_outcome` decimal(18,2) unsigned DEFAULT '0.00' COMMENT '钱包总支出额',
  `balance_fee` decimal(18,2) unsigned DEFAULT '0.00' COMMENT '钱包总可用余额',
  `check_sign`  varchar(100) DEFAULT '' COMMENT '用于安全检查，检查不通过为异常。',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户钱包';



DROP TABLE IF EXISTS `wallet_log`;
CREATE TABLE `wallet_log` (
 `id` varchar(36) NOT NULL COMMENT 'id',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户id',
  `number` varchar(32) NOT NULL DEFAULT '' COMMENT '流水号',
  `target_type` smallint(5) unsigned DEFAULT '0' COMMENT '业务类型，1：充值，2：提现  3：下单',
  `target_uuid` char(32) DEFAULT '' COMMENT '来源uuid（如提现uuid）',
  `action_type` smallint(5) unsigned DEFAULT '0' COMMENT '操作类型，1：充值，2：提现，3：订单',
  `fee` decimal(18,2) DEFAULT '0.00' COMMENT '变动的金额，正负数。',
  `original_account_json`  varchar(1000) DEFAULT '账户变更前的数据 json存储',
 `dispose_account_json`  varchar(1000) DEFAULT '账户变更后的数据 json存储',
  `status` smallint(5) unsigned DEFAULT '0' COMMENT '处理状态1，处理完成，0未完成',
  `result_type`  smallint(5) unsigned DEFAULT '0' COMMENT '处理结果，0：没有变更，1：有变更。',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户钱包流水记录表';


DROP TABLE IF EXISTS `user_top_up_order`;
CREATE TABLE `user_top_up_order` (
 `id` varchar(36) NOT NULL COMMENT 'id',
  `user_id` char(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `order_num` char(32) NOT NULL COMMENT '订单号',
  `amounts` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
  `currency` varchar(8) NOT NULL DEFAULT 'CNY' COMMENT '货币类型',
  `pay_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '实际支付金额',
  `exchange` decimal(10,4) NOT NULL DEFAULT '0.00' COMMENT '汇率',
  `status` tinyint(5) NOT NULL DEFAULT '0' COMMENT '支付状态：0待支付，1已支付，100已取消',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` tinyint(5) NOT NULL DEFAULT '0' COMMENT '0未删除1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充值订单';


DROP TABLE IF EXISTS `user_withdraw_cash_list`;
CREATE TABLE `user_withdraw_cash_list` (
 `id` varchar(36) NOT NULL COMMENT 'id',
  `user_id` varchar(36) NOT NULL COMMENT '申请用户uuid',
  `withdraw_way` tinyint(1) NOT NULL DEFAULT '1' COMMENT '提现（渠道）方式 1银行转账',
  `withdraw_status` smallint(5) unsigned DEFAULT '0' COMMENT '处理状态。 1发起申请（待审核理）前台显示处理中，2提现成功，3审核不通过',
  `number` char(32) DEFAULT '' COMMENT '提现单号',
  `receivable_account` varchar(32) DEFAULT '' COMMENT '收款账户',
  `name` varchar(30) DEFAULT '' COMMENT '收款人姓名',
  `address` varchar(100) DEFAULT '' COMMENT '开户行地址',
  `withdraw_fee` decimal(10,2) DEFAULT '0.00' COMMENT '提现金额',
  `content` varchar(500) DEFAULT '' COMMENT '审核不通过原因',
  `verify_user` varchar(32) DEFAULT '' COMMENT '审核人',
  `action_user` varchar(32) DEFAULT '' COMMENT '操作人',
  `action_at` datetime DEFAULT NULL COMMENT '审核时间',
  `sent_notice_at` datetime DEFAULT NULL COMMENT '发送通知时间',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='提现记录表';


DROP TABLE IF EXISTS `payment_list`;
CREATE TABLE `payment_list` (
  `id` varchar(36) NOT NULL COMMENT ' id',
  `user_id` varchar(36) NOT NULL DEFAULT '' COMMENT '用户uuid',
  `pay_num` bigint(20) unsigned DEFAULT '0' COMMENT '支付号',
  `trade_no` varchar(64) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '第三方付款成功交易号',
  `order_type` smallint(5) unsigned DEFAULT '0' COMMENT '订单类型 1充值 ',
  `order_uuid` char(32) DEFAULT '' COMMENT '充值订单表uuid',
  `pay_way` smallint(5) unsigned DEFAULT '0' COMMENT '付款方式 1微信 2支付宝 ',
  `pay_status` smallint(5) unsigned DEFAULT '0' COMMENT '支付状态 1 支付成功，2 支付失败 ',
  `step` smallint(5) unsigned DEFAULT '0' COMMENT '步骤 1 创建支付，2 支付回调通知',
  `post_status` smallint(5) unsigned DEFAULT '0' COMMENT '请求支付状态，1：成功，2：失败',
  `return_status` smallint(5) unsigned DEFAULT '0' COMMENT '回调状态，1：成功，2：失败',
  `total_fee` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '支付价格',
  `result_code`  varchar(30) DEFAULT '' COMMENT '第三方返回错误码',
  `err_code_str`  varchar(255) DEFAULT '' COMMENT '第三方返回的错误记录',
  `currency` varchar(8) NOT NULL DEFAULT 'HKD' COMMENT '货币类型',
  `ip2long` int(10) unsigned DEFAULT '0' COMMENT 'ip2long',
  `post_json` varchar(2000) DEFAULT '' COMMENT '提交post json 数据',
  `return_json` varchar(2000) DEFAULT '' COMMENT '回调post return data json',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `success_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付成功时间',
`procedure_kb` mediumint(8) unsigned DEFAULT '0' COMMENT '手续费比例，千位比。6/1000',

`procedure_fee`  decimal(10,2) unsigned DEFAULT '0.00' COMMENT '支付渠道收取手续费金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付记录列表';