package com.qingshan.mall.thirdparty;

import com.qingshan.mall.thirdparty.config.MailConfig;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallThirdPartyApplication.class)
public class MailTest {


    @Autowired
    private MailConfig mailConfig;

    @Test
    public void sendMessage() throws EmailException {
        //创建一个HtmlEmail实例对象
        HtmlEmail email=new HtmlEmail();
        //邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setHostName(mailConfig.getHostName());
        //设置发送的字符类型
        email.setCharset("utf-8");
        //设置收件人
        email.addTo("zhangyaxing521@qq.com");
        //发送人的邮箱为自己的，用户名可以随便填
        email.setFrom(mailConfig.getFrom(),mailConfig.getUserName());
        //设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
        email.setAuthentication(mailConfig.getFrom(),mailConfig.getAuthKey());
        //设置发送主题
        email.setSubject("测试");
        //设置发送内容
        email.setMsg("填写你的发送内容");
        email.send();//进行发送
    }
}
