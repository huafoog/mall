package com.qingshan.mall.product.exception;

import com.qingshan.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 集中处理异常
 * @author qingshan
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.qingshan.mall.product.controller")
public class MallExceptionControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handeVaildException(MethodArgumentNotValidException ex){
        log.error("数据校验出现问题{}，异常类型{}",ex.getMessage(), ex.getClass());

        BindingResult bindingResult = ex.getBindingResult();


        return R.error();
    }
}
