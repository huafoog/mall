package com.qingshan.mall.common.feign.feign.member;

import com.qingshan.common.core.constant.ProjectNameConstants;
import com.qingshan.common.core.dto.member.MemberDTO;
import com.qingshan.common.core.dto.member.MemberLoginDTO;
import com.qingshan.common.core.dto.member.MemberRegisterDTO;
import com.qingshan.common.core.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    R<MemberDTO> login(@RequestBody MemberLoginDTO vo);
}
