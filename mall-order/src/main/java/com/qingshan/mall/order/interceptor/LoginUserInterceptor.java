package com.qingshan.mall.order.interceptor;

import com.qingshan.common.core.constant.AuthServerConstant;
import com.qingshan.common.core.dto.member.MemberDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<MemberDTO> loginUser = new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MemberDTO attribute = (MemberDTO) request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);
        if (attribute != null){
            loginUser.set(attribute);
            return true;
        }else {

            StringBuffer requestURL = request.getRequestURL();
            //没登录就去登录
            request.getSession().setAttribute("msg","请先进行登录");
            response.sendRedirect("http://auth.mall.com/login.html?jumpUrl="+requestURL.toString());
            return false;
        }
    }
}