package com.qingshan.mall.auth.feign;

import com.qingshan.common.constant.ProjectNameConstants;
import com.qingshan.common.constant.enums.BizCodeEnum;
import com.qingshan.common.dto.member.MemberDTO;
import com.qingshan.common.dto.member.MemberLoginDTO;
import com.qingshan.common.dto.member.MemberRegisterDTO;
import com.qingshan.common.utils.R;
import com.qingshan.common.vo.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 会员服务
 * @author qingshan
 */
@FeignClient(ProjectNameConstants.MEMBER)
public interface RemoteMemberFeignService {
    /**
     * 注册
     * @param dto
     * @return
     */
    @PostMapping("/member/register")
    R register(@RequestBody MemberRegisterDTO dto);

    @PostMapping("/member/login")
    ResponseVO<MemberDTO> login(@RequestBody MemberLoginDTO vo);
}