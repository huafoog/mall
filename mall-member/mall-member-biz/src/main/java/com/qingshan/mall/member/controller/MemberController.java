package com.qingshan.mall.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.qingshan.common.core.constant.enums.BizCodeEnum;
import com.qingshan.common.core.dto.member.MemberDTO;
import com.qingshan.common.core.dto.member.MemberLoginDTO;
import com.qingshan.common.core.dto.member.MemberRegisterDTO;
import com.qingshan.mall.member.exception.PhoneExistException;
import com.qingshan.mall.member.exception.UserNameExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.qingshan.mall.member.entity.MemberEntity;
import com.qingshan.mall.member.service.MemberService;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.R;



/**
 * 会员
 *
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-03-18 16:22:26
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;


    @PostMapping("/register")
    public R register(@RequestBody MemberRegisterDTO vo){
        try{
            memberService.register(vo);
            //异常机制：通过捕获对应的自定义异常判断出现何种错误并封装错误信息
        }catch (PhoneExistException e){
            return R.failed(BizCodeEnum.PHONE_EXIST_EXCEPTION);
        }catch (UserNameExistException e){
            return R.failed(BizCodeEnum.USER_EXIST_EXCEPTION);
        }
        return R.ok();
    }

    @PostMapping("/login")
    public R<MemberDTO> login(@RequestBody MemberLoginDTO vo){
        MemberDTO entity = memberService.login(vo);
        if (entity != null){
            return R.ok(entity);
        }else {
            return R.failed(BizCodeEnum.LOGINACCT_PASSWORD_INVAILD_EXCEPTION);
        }
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params){

        return R.ok(memberService.queryPage(params));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Long id){
        return R.ok(memberService.getById(id));
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("member:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("member:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
