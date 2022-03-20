package com.qingshan.mall.thirdparty.service.impl;

import com.qingshan.mall.thirdparty.component.MailComponent;
import com.qingshan.mall.thirdparty.config.MailConfig;
import com.qingshan.mall.thirdparty.dto.mail.SendCodeInputDTO;
import com.qingshan.mall.thirdparty.service.MailService;
import lombok.AllArgsConstructor;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

/**
 * 邮箱服务
 * @author qingshan
 */
@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private MailComponent mailComponent;

    /**
     * 发送消息
     */
    @Override
    public void sendCodeMessage(SendCodeInputDTO dto) {
        //创建一个HtmlEmail实例对象
        mailComponent.sendCodeMessage(dto);
    }
}
