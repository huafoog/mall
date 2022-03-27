package com.qingshan.mall.auth.controller;

import com.qingshan.common.core.dto.member.MemberDTO;
import com.qingshan.common.core.dto.member.MemberLoginDTO;
import com.qingshan.common.core.constant.AuthServerConstant;
import com.qingshan.common.core.utils.R;
import com.qingshan.mall.auth.service.AuthService;
import com.qingshan.mall.auth.utils.VerifyCode;
import com.qingshan.mall.auth.vo.UserRegisterVO;
import com.qingshan.mall.common.feign.feign.member.RemoteMemberFeignService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 登录控制器
 * @author qingshan
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RemoteMemberFeignService memberFeignService;
    private final RedisTemplate redisTemplate;

    /* 获取验证码图片*/

    @RequestMapping("/getVerifyCode")
    public void getVerificationCode(HttpServletResponse response, HttpServletRequest request) {

        try {

            int width=200;

            int height=69;

            BufferedImage verifyImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

//生成对应宽高的初始图片

            String randomText = VerifyCode.drawRandomText(width,height,verifyImg);

//单独的一个类方法，出于代码复用考虑，进行了封装。

//功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符

            request.getSession().setAttribute("verifyCode", randomText);
//必须设置响应内容类型为图片，否则前台不识别
            response.setContentType("image/png");
//获取文件输出流
            OutputStream os = response.getOutputStream();
            ImageIO.write(verifyImg,"png",os);

            os.flush();

            os.close();//关闭流

        } catch (IOException e) {

        }

    }

    /**
     * //TODO 重定向携带数据，利用session原理。将数据放在session中。只要跳到下一个页面，取出数据以后，session里面的数据就会删掉
     * //TODO  1、分布式下的session问题
     * RedirectAttributes redirectAttributes 模拟重定向携带数据
     * @param vo
     * @return
     */
    @PostMapping("/register")
    public R register(@Validated @RequestBody UserRegisterVO vo){
        //1、校验验证码
        R result = authService.register(vo);
        if (!result.isSuccess()){
            return R.failed(result.getMsg());
        }
        //注册成功回到登录页
        //return "redirect:http://auth.gulimall.com/login.html";
        return R.ok();
    }

    @GetMapping("/email/sendCode")
    public R sendCode(@RequestParam("email") String email){
       return authService.sendCode(email);
    }


    @PostMapping("/login")
    public R login(@Validated @RequestBody MemberLoginDTO vo, HttpSession session){
        R<MemberDTO> res = memberFeignService.login(vo);
        if (res.isSuccess()){
            session.setAttribute(AuthServerConstant.LOGIN_USER,res.getData());
            return R.ok();
        }else{
            return res;
        }

    }


}
