package com.qingshan.common.vo;

import com.qingshan.common.constant.CommonConstants;
import com.qingshan.common.constant.enums.BizCodeEnum;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author qingshan
 */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回标记：成功标记=0，失败标记=1
     */
    @Getter
    @Setter
    private int code;

    /**
     * 返回信息
     */
    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    public Boolean isSuccess() {
        return this.getCode() == CommonConstants.SUCCESS;
    }


    public static <T> ResponseVO<T> ok() {
        return restResult(null, CommonConstants.SUCCESS, null);
    }

    public static <T> ResponseVO<T> ok(T data) {
        return restResult(data, CommonConstants.SUCCESS, null);
    }

    public static <T> ResponseVO<T> ok(T data, String msg) {
        return restResult(data, CommonConstants.SUCCESS, msg);
    }

    public static <T> ResponseVO<T> failed() {
        return restResult(null, CommonConstants.FAIL, null);
    }

    public static <T> ResponseVO<T> failed(String msg) {
        return restResult(null, CommonConstants.FAIL, msg);
    }
    public static <T> ResponseVO<T> failed(BizCodeEnum codeEnum) {
        return restResult(null, codeEnum.getCode(),codeEnum.getMsg());
    }

    public static <T> ResponseVO<T> failed(T data) {
        return restResult(data, CommonConstants.FAIL, null);
    }

    public static <T> ResponseVO<T> failed(T data, String msg) {
        return restResult(data, CommonConstants.FAIL, msg);
    }

    private static <T> ResponseVO<T> restResult(T data, int code, String msg) {
        ResponseVO<T> apiResult = new ResponseVO<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

}