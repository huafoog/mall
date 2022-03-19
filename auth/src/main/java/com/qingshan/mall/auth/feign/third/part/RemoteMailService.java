package com.qingshan.mall.auth.feign.third.part;

import com.qingshan.common.constant.ProjectNameConstants;
import com.qingshan.common.utils.R;
import com.qingshan.mall.auth.mail.SendCodeInputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 */
@FeignClient(ProjectNameConstants.THIRD_PARTY)
public interface RemoteMailService {
    /**
     * 发送验证码
     * @param dto
     * @return
     */
    @PostMapping("/mail/sendCodeMessage")
    R sendCodeMessage(@RequestBody SendCodeInputDTO dto);
}
