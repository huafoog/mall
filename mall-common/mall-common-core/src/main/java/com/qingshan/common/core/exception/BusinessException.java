/*
 *
 *      Copyright (c) 2018-2025, theatre All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: theatre
 *
 */

package com.qingshan.common.core.exception;

import com.qingshan.common.core.constant.enums.BizCodeEnum;

/**
 * 业务异常
 * @author qs
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -7285211528095468156L;

	private BizCodeEnum codeEnum;


	public BusinessException() {
	}

	public BusinessException(BizCodeEnum code) {
		super(code.getMsg());
		codeEnum = code;
	}

	public BusinessException(String msg) {
		super(msg);
	}

}
