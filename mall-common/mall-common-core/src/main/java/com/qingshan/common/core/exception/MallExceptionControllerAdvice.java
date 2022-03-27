package com.qingshan.common.core.exception;

import com.qingshan.common.core.constant.enums.BizCodeEnum;
import com.qingshan.common.core.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 集中处理所有异常
 */
@Slf4j
@ComponentScan
@RestControllerAdvice
public class MallExceptionControllerAdvice {


    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e){
        log.error("数据校验出现问题{}，异常类型：{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        Map<String,String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError)->{
            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return R.failed(BizCodeEnum.VAILD_EXCEPTION.getCode(), BizCodeEnum.VAILD_EXCEPTION.getMsg());
    }
    @ExceptionHandler(value= BindException.class)
    public R handleVaildException(BindException e){
        log.error("数据校验出现问题{}，异常类型：{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        Map<String,String> errorMap = new HashMap<>();

        FieldError first = bindingResult.getFieldErrors().stream().findFirst().get();

        String msg = "";

        if (first != null){
            msg = first.getDefaultMessage();
//            errorMap.put(first.getField(),first.getDefaultMessage());
        }
        return R.failed(BizCodeEnum.VAILD_EXCEPTION.getCode(), msg);
    }

    @ExceptionHandler(value= RRException.class)
    public R handleRRException(RRException e){
        return R.failed(e.getBizCode());
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){

        log.error("错误：",throwable);
        return R.failed(BizCodeEnum.UNKNOW_EXCEPTION.getCode(), BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public R handleException(Exception throwable){

        log.error("错误：",throwable);
        return R.failed(BizCodeEnum.UNKNOW_EXCEPTION.getCode(), BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }


}
