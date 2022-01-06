package com.qingshan.mall.thirdparty;

import com.qingshan.mall.thirdparty.config.OssConfig;
import com.qingshan.mall.thirdparty.service.OssService;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallThirdPartyApplication.class)
public class MallThirdPartyApplicationTests {

    @Autowired
    private OssService ossService;

    @Autowired
    private OssConfig config;
    @Test
    public void contextLoads() throws Exception {

        File file = new File("C:\\Users\\qingshan\\OneDrive\\图片\\头像\\v2-1e869a96eb5c1d740dadb1c759939f5c_b.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        System.out.println(ossService.uploadFile(multipartFile));;
//        System.out.println(config.toString());
    }


}
