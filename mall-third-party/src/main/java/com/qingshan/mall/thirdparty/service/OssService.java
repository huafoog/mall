package com.qingshan.mall.thirdparty.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    /**
     * 上传文件
     * @param file 文件流
     * @return
     * @throws Exception
     */
    String uploadFile (MultipartFile file) throws Exception;
}
