package com.qingshan.mall.thirdparty.service.impl;

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

    private MailConfig mailConfig;

    /**
     * 发送消息
     */
    @Override
    public void sendCodeMessage(SendCodeInputDTO dto) {
        //创建一个HtmlEmail实例对象
        HtmlEmail email=new HtmlEmail();
        //邮箱的SMTP服务器，163邮箱的是smtp.163.com,qq邮箱为smtp.qq.com
        email.setHostName(mailConfig.getHostName());
        //设置发送的字符类型
        email.setCharset("utf-8");
        //设置收件人
        try {
            email.addTo(dto.getEmail());
            //发送人的邮箱为自己的，用户名可以随便填
            email.setFrom(mailConfig.getFrom(),mailConfig.getUserName());
            //设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
            email.setAuthentication(mailConfig.getFrom(),mailConfig.getAuthKey());
            //设置发送主题
            email.setSubject("验证码");
            //设置发送内容
            email.setMsg(String.format("你的验证码为【%s】",dto.getCode()));
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
