/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.qingshan.common.core.exception;

import com.qingshan.common.core.constant.enums.BizCodeEnum;
import lombok.Data;

/**
 * 自定义异常
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class RRException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;

    private BizCodeEnum bizCode;
    
    public RRException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public RRException(BizCodeEnum bizCodeEnum) {
		this.bizCode = bizCodeEnum;
	}
	
	public RRException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public RRException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public RRException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	
}
