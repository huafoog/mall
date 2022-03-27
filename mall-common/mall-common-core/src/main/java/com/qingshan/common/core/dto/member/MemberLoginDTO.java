package com.qingshan.common.core.dto.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *
 */
@Data
public class MemberLoginDTO {
    /**
     * 登录账号
     */
    @NotBlank(message = "未获取到登录账号")
    private String account;

    /**
     * 登录密码
     */
    @NotBlank(message = "未获取到登录密码")
    private String password;
}
