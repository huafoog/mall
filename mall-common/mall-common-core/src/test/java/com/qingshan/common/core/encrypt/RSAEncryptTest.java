package com.qingshan.common.core.encrypt;
import java.util.Date;

import com.qingshan.common.core.dto.member.MemberDTO;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class RSAEncryptTest {

    private String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDaue7eZicmgDPUVHTOJtV+IreRl/a2xoahvABY1dVykKjBnLMlk4Nxa3olk0jTMDS1qMCbf8tSVJ1/MSn/XPyzytuK0xlK3fbFabTGGzXwXqGvyQRZwTwGMKxjd7UHOScuLBc5D7a+krbVOSvh9GMJUmQiKwM4kxsqaLdMmcI7R8ghXBUHh3a6R2eZI/HcEclmny2xkbEm9LAULUEhScH9mOhQcduFsmhWmwN0ob7kYeKI8S4m5zQG7avvO/vQgzo/sPy6UQKmmge/Xo4IK0mKopxI5pmLjYa7uEhYe5AkkYL7IUufo9GVJxJ2mhQ+wkdzDeZAgyXWXnc4G+SUwtRHAgMBAAECggEAJYJzY3JzTlf4C3a8XUotnKcJgg9kgO3E0uGx0YpCQ5ZpSmK8GjqU9RNwbBc5CvaeY3TgKwqcbK8UH2boA+IHeDHWupT/JmawViSNmte5xVIFOmcntti7mXgpaBl0UxvLC1EGXoPCXCY+8owOaX6GqWeWwoFL2RzpMLmqjZlkxNN2ODju8BwcfkF2/eBItchmvGW1zOOwIJZuiHCv5sipKPFfofPUfwgJ6pwlwvVBLRcNlx6Prjj9T6s+PorB3QHlG2tZExpd1bTpPhnlVK+0MP+PwPmvcxhiGzGHSQKnP+3YoCkzQs9V4+VlBe8NEUSC1SHtjCab46VvuxROFI8YEQKBgQD89F4NjqUwJkR7XUmHYtosPYYXisM88RKM0bZ/g89VTgNzNUqAN6n0w0EgKrCp8H+Rdza8cNzBQhwHbatcBASutoYygTrGe0XljdQPIvMjiHBh91S1iL4oBC7EfCSbibmlZv5XH6zbKgyBrQgivhnCzO+ru5JaP4i7wZcFv6XsTwKBgQDdXBIR7R42lGwgwG/HkKFcCxblNg51kiTCl+vZbhZojKFnFxyMvGEgccxFBq2t4AoSPnu39nyfFFixvrNZ5iXMz1XUKqiPq80nP/qe/7WLDsxHp3pUJrYyJn8MlW8Glgjwam8uz5UX1AMPidvbfe89tD7sDEKW68nsvhA4t4VCiQKBgQC5d6MOZayQpT5JBZlH/x7gmSucT03ffRKQ1aLt1ANNFKpK6qP5Ztbic9docmSd9YxTaAlmGYqC2KoceDjQetHEGGhE16P0q6YQLfFyN7n2L6/W5LXjdKOYS5jO0Ac3U8AemmmaD8t51lbdLbVtY90Xi5OfYyRnr3Xd1I+a024g2QKBgHXHJT4wgeDce+O5QEBYreJBbD8s0eiwh6qaFr0rm/SozsABRgmDudH/eoZHlWVsTS+D22P4XDpwIvj/1IHCuN4Oe0ZWJDW9XXzCx+CNgdSpXPvLGoVny41HelYBtH82mS8CEoI/pL526eH8oeRZT0/vdYM2yr9uWZQDVLI4w0ixAoGBAIj059mqxzJ0GyWMep9Xud4xuj5qOZiXkiEwArEenHBTMq94Y8ltZ5LPxLCt38GN0ehykHfKGDO57Yy/7lygp7kZk8foDVWGr83POR2BbsQ2f2HMqSKTEL5hTbvD0xkvwp646SJ25M9UVhbweA+BDtqVkprHI7pWu/INq09mQw9c";

    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2rnu3mYnJoAz1FR0zibVfiK3kZf2tsaGobwAWNXVcpCowZyzJZODcWt6JZNI0zA0tajAm3/LUlSdfzEp/1z8s8rbitMZSt32xWm0xhs18F6hr8kEWcE8BjCsY3e1BzknLiwXOQ+2vpK21Tkr4fRjCVJkIisDOJMbKmi3TJnCO0fIIVwVB4d2ukdnmSPx3BHJZp8tsZGxJvSwFC1BIUnB/ZjoUHHbhbJoVpsDdKG+5GHiiPEuJuc0Bu2r7zv70IM6P7D8ulECppoHv16OCCtJiqKcSOaZi42Gu7hIWHuQJJGC+yFLn6PRlScSdpoUPsJHcw3mQIMl1l53OBvklMLURwIDAQAB";


    @Test
    public void initKey() {
        // 生成一对公私钥
        Map<String, Key> keys = new HashMap<>(2);
        try {
            keys = RSAEncrypt.initKey();
        } catch (Exception e) {
            System.out.println("init RSA key error，message is" + e.getMessage());
            System.exit(-1);
        }

        // 获得私钥
        Key privateKey = keys.get(RSAEncrypt.PRIVATE_KEY);
        // 私钥Base64编码字符串
        String base64PrivateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
        System.out.println("base64 privateKey String is:" + base64PrivateKeyStr);

        // 获得公钥
        Key publicKey = keys.get(RSAEncrypt.PUBLIC_KEY);
        String base64PublicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        System.out.println("base64 publicKey String is:" + base64PublicKeyStr);
    }

    @Test
    public void RSASignature() {
        String data = getContent();
        System.out.println(data);
    }

    private String getContent(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(0L);
        memberDTO.setLevelId(0L);
        memberDTO.setUsername("123");
        memberDTO.setNickname("123");
        memberDTO.setMobile("123");
        memberDTO.setEmail("123");
        memberDTO.setHeader("123");
        memberDTO.setGender(0);
        memberDTO.setBirth(new Date());
        memberDTO.setCity("123");
        memberDTO.setJob("123");
        memberDTO.setSign("123");
        memberDTO.setSourceType(0);
        memberDTO.setIntegration(0);
        memberDTO.setGrowth(0);
        memberDTO.setStatus(0);
        memberDTO.setCreateTime(new Date());

        return RSASignature.getSignData(memberDTO);
    }

    @Test
    public void test() throws Exception {
        String signContent = getContent();
        String encryptionContent = getContent();
        PublicKey publicKey1 = RSAEncrypt.getPublicKey(publicKey);
        PrivateKey privateKey1 = RSAEncrypt.getPrivateKey(privateKey);
        String base64PublicKeyStr = Base64.encodeBase64String(publicKey1.getEncoded());
        String base64PrivateKeyStr = Base64.encodeBase64String(privateKey1.getEncoded());
        // 获得私钥
        // 签名
        String signString = RSAEncrypt.signByPrivateKey(signContent, base64PrivateKeyStr);
        System.out.println("sign by privateKey ,signString is: " + signString);

        boolean verifySignResult = RSAEncrypt.verifySignByPublicKey(signContent, signString, base64PublicKeyStr);
        System.out.println("verify sign result is: " + verifySignResult);


        // 加密
        String encryptionString = RSAEncrypt.encryptByPublicKey(encryptionContent, base64PublicKeyStr);

        String decryptionString = RSAEncrypt.decryptByPrivateKey(encryptionString, base64PrivateKeyStr);
        System.out.println("decryptionString by privateKey ,decryptionString is: " + decryptionString);

    }


    public void verifySignByPublicKey(){
        String sign = "XOT2hzCDLSFzw7caS4V7mVYpPL03Rm2JY4lrZ6dVtJsScjeEI0HMKSIjOvcSmZEwAEVkU928xgzTlJZXaWmmZiymX4dr0xG-n-LOjCRAIW5UGMzkdxF7zSPNPm8aqP5B4nf4vv6nNQqYXduLeH9lG6wOOa8EZu3U_FrKVu6RHMI";
    }
}
