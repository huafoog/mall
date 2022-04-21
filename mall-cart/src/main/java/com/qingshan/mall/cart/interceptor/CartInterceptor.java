package com.qingshan.mall.cart.interceptor;

import com.qingshan.common.core.constant.AuthServerConstant;
import com.qingshan.common.core.dto.member.MemberDTO;
import com.qingshan.mall.cart.constant.CartConstant1;
import com.qingshan.mall.cart.dto.UserInfoDTO1;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Description: 在执行目标方法之前，判断用户的登录状态。并封装传递给目标请求
 * @Author: WangTianShun
 * @Date: 2020/11/21 14:56
 * @Version 1.0
 */
public class CartInterceptor implements HandlerInterceptor {
    //ThreadLocal同一个线程共享数据
    public static ThreadLocal<UserInfoDTO1> threadLocal = new ThreadLocal<>();
    /**
     * 在目标方法执行之前拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfoDTO1 userInfoTo = new UserInfoDTO1();
        HttpSession session = request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (member != null){
            //用户登录
            userInfoTo.setUserId(member.getId());

        }
        Cookie[] cookies = request.getCookies();
        if (cookies!=null && cookies.length >0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (name.equals(CartConstant1.TEMP_USER_COOKIE_NAME)){
                    userInfoTo.setUserKey(cookie.getValue());
                    userInfoTo.setTempUser(true);
                }
            }
        }

        //如果没有临时用户，一定保存一个临时用户
        if (StringUtils.isEmpty(userInfoTo.getUserKey())){
            String uuid = UUID.randomUUID().toString();
            userInfoTo.setUserKey(uuid);
            setCookie(response,userInfoTo.getUserKey());
        }
        //目标方法执行之前
        threadLocal.set(userInfoTo);
        return true;
    }

    /**
     * 业务执行之后 分配临时用户，让浏览器保存
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserInfoDTO1 userInfoTo = threadLocal.get();
        //如果没有临时用户，第一次访问购物车就添加临时用户
        if (!userInfoTo.isTempUser()){
            //持续的延长用户的过期时间
            setCookie(response,userInfoTo.getUserKey());
        }
    }


    private void setCookie(HttpServletResponse response,String key){
        //持续的延长用户的过期时间
        Cookie cookie = new Cookie(CartConstant1.TEMP_USER_COOKIE_NAME, key);
        cookie.setDomain(CartConstant1.DOMAIN);
        cookie.setMaxAge(CartConstant1.TEMP_USER_COOKIE_TIMEOUT);
        response.addCookie(cookie);
    }

}
