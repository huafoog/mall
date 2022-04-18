package com.qingshan.common.core.encrypt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;

/**
 * RSA 加密解密
 */
@Slf4j
public class RSAEncrypt {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

    /**
     * MAX_DECRYPT_BLOCK应等于密钥长度/8（1byte=8bit），所以当密钥位数为2048时，最大解密长度应为256.
     * 128 对应 1024，256对应2048
     */
    private static final int KEY_SIZE = 2048;

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    // RSA最大解密密文大小
//	private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = KEY_SIZE / 8;


    //map对象中存放公私钥
    public static Map<String, Key> initKey() throws Exception {
        //获得对象 KeyPairGenerator 参数 RSA 2048个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(KEY_SIZE);
        //通过对象 KeyPairGenerator 获取对象KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //公私钥对象存入map中
        Map<String, Key> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }


    /**
     * RSA私钥签名
     * @param content 待签名数据
     * @param privateKey 私钥
     * @return 签名值
     */
    public static String signByPrivateKey(String content, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64URLSafe(signed), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.warn("sign error, content: {}, priKey: {}", content, privateKey);
            log.error("sign error, message is {}", e.getMessage());
        }
        return null;
    }

    /**
     * 通过公钥加密
     * RSA 加密超长需要进行分段加密和分段解密
     * @param plainText 明文
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encryptByPublicKey(String plainText, String publicKey) {
        try {
            PublicKey pubKey = getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            // 分段加密
            byte[] data = plainText.getBytes(StandardCharsets.UTF_8);
            // 加密时超过117字节就报错。为此采用分段加密的办法来加密
            byte[] enBytes = null;
            for (int i = 0; i < data.length; i += MAX_ENCRYPT_BLOCK) {
                // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
                byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + MAX_ENCRYPT_BLOCK));
                enBytes = ArrayUtils.addAll(enBytes, doFinal);
            }
            return Base64.encodeBase64String(enBytes);
        } catch (Exception e) {
            log.error("encrypt error", e);
        }
        return null;
    }

    /**
     * 通过私钥解密
     * RSA 加密超长需要进行分段加密和分段解密
     * @param enStr 加密字符串
     * @param privateKey 私钥
     * @return 明文
     */
    public static String decryptByPrivateKey(String enStr, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, priKey);

            // 分段解密
            // 64位解码加密后的字符串
            byte[] data = Base64.decodeBase64(enStr);
            // 解密时超过字节报错。为此采用分段解密的办法来解密
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.length; i += MAX_DECRYPT_BLOCK) {
                byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + MAX_DECRYPT_BLOCK));
                sb.append(new String(doFinal));
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("decrypt error", e);
        }
        return null;
    }


    /**
     * 从公钥字符串中获取公钥
     * @param key Base64的公钥字符串
     * @return 公钥
     * @throws Exception 异常
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 从私钥字符串中获取私钥
     * @param key Base64的私钥字符串
     * @return 私钥
     * @throws Exception 异常
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key.getBytes(StandardCharsets.UTF_8));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 通过公钥验签
     * @param content 验签内容
     * @param sign  签名
     * @param publicKey 公钥
     * @return 验签结果
     */
    public static boolean verifySignByPublicKey(String content, String sign, String publicKey) {
        try {
            PublicKey pubKey = getPublicKey(publicKey);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            return signature.verify(Base64.decodeBase64(sign.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.warn("sign error, content: {}, sign: {}, pubKey: {}", content, sign, publicKey);
            log.error("sign error", e);
        }
        return false;
    }
}


