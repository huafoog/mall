package com.qingshan.mall.auth.service.impl;

import com.alibaba.nacos.client.utils.StringUtils;
import com.qingshan.common.constant.enums.BizCodeEnum;
import com.qingshan.common.dto.member.MemberRegisterDTO;
import com.qingshan.common.utils.R;
import com.qingshan.common.constant.AuthServerConstant;
import com.qingshan.mall.auth.feign.RemoteMemberFeignService;
import com.qingshan.mall.auth.feign.third.part.RemoteMailService;
import com.qingshan.mall.auth.mail.SendCodeInputDTO;
import com.qingshan.mall.auth.service.AuthService;
import com.qingshan.mall.auth.vo.UserRegisterVO;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final StringRedisTemplate stringRedisTemplate;

    private final RemoteMemberFeignService memberFeignService;

    private final RemoteMailService remoteMailService;


    /**
     * //TODO 重定向携带数据，利用session原理。将数据放在session中。只要跳到下一个页面，取出数据以后，session里面的数据就会删掉
     * //TODO  1、分布式下的session问题
     * RedirectAttributes redirectAttributes 模拟重定向携带数据
     * @param vo
     * @return
     */
    @Override
    public R register(@Validated UserRegisterVO vo){
        //1、校验验证码
        String code = vo.getCode();
        String s = (String) stringRedisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getEmail());
        if (!StringUtils.isEmpty(s)) {
            if (code.equals(s.split("_")[0])) {
                //验证码通过,删除缓存中的验证码；令牌机制
                stringRedisTemplate.delete(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());

                MemberRegisterDTO memberRegisterDTO = new MemberRegisterDTO();
                memberRegisterDTO.setUserName(vo.getUserName());
                memberRegisterDTO.setPassword(vo.getPassword());
                memberRegisterDTO.setPhone(vo.getPhone());
                //真正注册调用远程服务注册
                R r = memberFeignService.register(memberRegisterDTO);
                return r;
            } else {
                return R.error("验证码错误");
            }
        }
        return R.error("验证码错误");
    }

    @Override
    public R sendCode(String email){
        //1、接口防刷
        String prefixPhone = AuthServerConstant.SMS_CODE_CACHE_PREFIX + email;
        String redisCode = (String) stringRedisTemplate.opsForValue().get(prefixPhone);
        if (!StringUtils.isEmpty(redisCode)){
            long l = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() -l < 60000){
                //60秒内不能再发
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(),BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }

        //2、验证码的再次校验。redis 存key-phone, value-code   sms:code:18896736055 ->12345
        String code = String.valueOf((int)((Math.random() + 1) * 100000));
        //redis缓存验证码   防止同一个phone在60s内再次发送验证码  set(K var1, V var2, long var3, TimeUnit var5)
        stringRedisTemplate.opsForValue().set(prefixPhone,code + "_" + System.currentTimeMillis(),10, TimeUnit.MINUTES);

        SendCodeInputDTO sendCodeInputDTO = new SendCodeInputDTO();
        sendCodeInputDTO.setCode(code);
        sendCodeInputDTO.setEmail(email);
        remoteMailService.sendCodeMessage(sendCodeInputDTO);
        return R.ok();
    }
}
