package com.qingshan.mall.thirdparty.service;

import com.qingshan.mall.thirdparty.dto.mail.SendCodeInputDTO;

public interface MailService {
    /**
     * 发消息
     * @param dto
     */
    void sendCodeMessage(SendCodeInputDTO dto);
}
