package com.qingshan.mall.auth.service;

import com.qingshan.common.core.utils.R;
import com.qingshan.mall.auth.vo.UserRegisterVO;
import org.springframework.validation.annotation.Validated;

public interface AuthService {
    R register(@Validated UserRegisterVO vo);

    R sendCode(String email);
}
