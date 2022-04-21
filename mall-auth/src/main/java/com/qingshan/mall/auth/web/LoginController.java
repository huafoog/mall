package com.qingshan.mall.auth.web;

import com.qingshan.common.core.constant.AuthServerConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login.html")
    public String loginPage(HttpSession session
            , Model model
            , @RequestParam(name = "jumpUrl",required = false) String jumpUrl){
        Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);
        if(StringUtils.isBlank(jumpUrl)){
            jumpUrl =  "http://mall.com";
        }
        model.addAttribute("jumpUrl",jumpUrl);
        if (attribute == null) {
            //没登录
            return "login";
        } else{
            return "redirect:"+jumpUrl;
        }
    }
}
