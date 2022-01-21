package com.qingshan.common.validate;

import com.qingshan.common.constant.LikeConstants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * like 约束验证器 禁用 % 和 _
 * @author qingshan
 */
public class LikeConstraintValidator implements ConstraintValidator<LikeValidator, Object> {
	@Override
	public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
		if (o == null){
			return true;
		}
		// 具体的校验规则
		for (String s : LikeConstants.like) {
			if (o.toString().contains("%")  || o.toString().contains("_")){
				return false;
			}
		}
		return true;
	}
}
