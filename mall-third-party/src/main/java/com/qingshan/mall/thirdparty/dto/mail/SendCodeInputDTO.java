package com.qingshan.mall.thirdparty.dto.mail;

import lombok.Data;

import java.io.Serializable;

/**
 * 发送验证码接收参数
 * @author qingshan
 */
@Data
public class SendCodeInputDTO implements Serializable {
    /**
     * 当前验证码
     */
    private String code;
    /**
     * 接收的账号
     */
    private String email;
}
