package com.qingshan.mall.thirdparty.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.qingshan.common.utils.R;
import com.qingshan.mall.thirdparty.config.OssConfig;
import com.qingshan.mall.thirdparty.dto.mail.SendCodeInputDTO;
import com.qingshan.mall.thirdparty.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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
    public R sendCodeMessage(@RequestBody SendCodeInputDTO dto){
        mailService.sendCodeMessage(dto);
        return  R.ok();
    }
}
