package com.qingshan.mall.thirdparty.dto.mail;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "未获取到需要发送的验证码信息")
    private String code;
    /**
     * 接收的账号
     */
    @Email(message = "请输入正确的email")
    @NotBlank(message = "未获取到邮箱账号")
    private String email;
}
