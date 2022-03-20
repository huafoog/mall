package com.qingshan.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshan.common.dto.member.MemberDTO;
import com.qingshan.common.dto.member.MemberLoginDTO;
import com.qingshan.common.dto.member.MemberRegisterDTO;
import com.qingshan.common.utils.PageUtils;
import com.qingshan.mall.member.entity.MemberEntity;
import com.qingshan.mall.member.exception.PhoneExistException;
import com.qingshan.mall.member.exception.UserNameExistException;

import java.util.Map;

/**
 * 会员
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-03-18 16:22:26
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void register(MemberRegisterDTO vo);
    void checkPhoneUnique(String phone) throws PhoneExistException;
    void checkUserNameUnique(String userName) throws UserNameExistException;

    /**
     * 登录
     * @param vo
     * @return
     */
    MemberDTO login(MemberLoginDTO vo);
}

