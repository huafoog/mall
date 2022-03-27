package com.qingshan.mall.thirdparty.controller;

import com.qingshan.common.core.utils.R;
import com.qingshan.mall.thirdparty.dto.mail.SendCodeInputDTO;
import com.qingshan.mall.thirdparty.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Oss控制
 * @author qingshan
 */
@RestController
@RequestMapping("/mail")
@AllArgsConstructor
public class MailController {
    private final MailService mailService;

    /**
     * 发送验证码
     * @param dto
     * @return
     */
    @PostMapping("/sendCodeMessage")
    public R sendCodeMessage(@Validated @RequestBody SendCodeInputDTO dto){
        mailService.sendCodeMessage(dto);
        return  R.ok();
    }
}
