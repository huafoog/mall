package com.qingshan.mall.thirdparty.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.qingshan.mall.thirdparty.config.OssConfig;
import com.qingshan.mall.thirdparty.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssConfig ossConfig;

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        //读取工具类的数据
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getKeyId();
        String accessKeySecret = ossConfig.getKeySecret();
        String bucketName = ossConfig.getBucketName();
        //连接oss客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流
        InputStream inputStream = file.getInputStream();
        //这里最好对文件路径或名字做一下处理，防止文件名或路径重复导致文件丢失或者被覆盖
        String url = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
        //上传
        ossClient.putObject(bucketName, url, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        return "https://"+bucketName+"."+endpoint+"/"+url;
    }
}
