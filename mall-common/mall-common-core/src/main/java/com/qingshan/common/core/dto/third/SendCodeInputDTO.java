package com.qingshan.common.core.dto.third;

import lombok.Data;

import java.io.Serializable;

@Data
public class SendCodeInputDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当前验证码
     */
    private String code;
    /**
     * 接收的账号
     */
    private String email;
}
