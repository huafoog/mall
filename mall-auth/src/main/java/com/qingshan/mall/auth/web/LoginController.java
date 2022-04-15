package com.qingshan.mall.auth.web;

import com.qingshan.common.core.constant.AuthServerConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login.html")
    public String loginPage(HttpSession session){
        Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (attribute == null) {
            //没登录
            return "login";
        } else{
            return "redirect:http://mall.com";
        }
    }
}
