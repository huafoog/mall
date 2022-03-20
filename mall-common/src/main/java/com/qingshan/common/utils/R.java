/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.qingshan.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.qingshan.common.constant.enums.BizCodeEnum;
import com.qingshan.common.exception.RRException;
import lombok.Data;
import org.apache.http.HttpStatus;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class R<T> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 0);
		put("success", true);
		put("msg", "success");
	}

	/**
	 * 设置成功数据
	 * @param data 要返回的数据
	 * @param <T> 返回的数据类型
	 * @return
	 */
	public static <T> R<T> ok(T data){
		R r = new R();
		r.put("data",data);
		return r;
	}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.put("success", false);
		return r;
	}

	public static R error(BizCodeEnum bizCodeEnum,int code) {
		R r = new R();
		r.put("code", code);
		r.put("success", false);
		r.put("msg", String.format("%s  【错误代码：%s】",bizCodeEnum.getMsg(),bizCodeEnum.getCode()));
		return r;
	}
	public static R error(BizCodeEnum bizCodeEnum) {
		R r = new R();
		r.put("code", 500);
		r.put("success", false);
		r.put("msg", String.format("%s  【错误代码：%s】",bizCodeEnum.getMsg(),bizCodeEnum.getCode()));
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		r.put("success", true);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		r.put("success", true);
		return r;
	}
	
	public static R ok() {
		return new R();
	}
	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public  Integer getCode() {

		return (Integer) this.get("code");
	}
	public String getMsg() {
		return (String)this.get("msg");
	}

	public  boolean getSuccess() {

		return getCode()== 0;
	}

	public Object getData(){
		return this.get("data");
	}

}
