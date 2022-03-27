package com.qingshan.mall.common.feign.feign.third;

import com.qingshan.common.core.constant.ProjectNameConstants;
import com.qingshan.common.core.dto.third.SendCodeInputDTO;
import com.qingshan.common.core.utils.R;
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
