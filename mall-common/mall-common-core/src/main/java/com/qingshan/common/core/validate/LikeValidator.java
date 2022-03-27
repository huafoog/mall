package com.qingshan.common.core.validate;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LikeConstraintValidator.class)
public @interface LikeValidator {
	String message() default  "筛选条件不能包含【%】或【_】";

	// groups 和 payload 这两个parameter 必须包含,不然会报错
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
