package com.qingshan.common.core.encrypt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * RSA签名规则
 * 构造待签名字符串。待签名字符的生成规则如下：
 * 所有发送到后端的请求参数均加入签名，除了sign字端
 * 所有参与签名的请求参数都按照名称字符升序排列(参数名称不允许相同)
 * 如果参数值带有中文， 需要制定字符集编码为UTF-8
 * 如果参数值为空，那么该参数不参与签名
 * 将请求参数用`&`拼接起来(按照名称字符升序排列)
 * 用算法RSA，对待签名字符串进行加密， 生成的签名数据， 就是公共参数中sign的值
 */
public class RSASignature {

    private final static String[] excludeDefaultField = new String[]{ "createTime","updateTime","createMan","updateMan","sign" };

    private static List<String> excludeField = Arrays.asList(excludeDefaultField);


    /**
     * 加密
     * @param data 加密数据
     * @param privateKey 私钥
     * @return
     */
    public static String sign(Object data,String privateKey){
        String signData = getSignData(data);
        return RSAEncrypt.signByPrivateKey(signData,privateKey);
    }

    public static String getSignData(Object data){
        return getSignData(data,null);
    }
    public static String getSignData(Object data, ArrayList<String> exclude){
        //Object转Map
        Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(data));


        //转化为treeMap
        TreeMap<String, Object> treeMap = new TreeMap<>(map);
        // 将参数进行字典排序
        StringBuilder sb = new StringBuilder();
        // 组成待签名字符串
        sb.delete(0, sb.length()); // 清空StringBuilder
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {//如果是集合则取第一条
            if (excludeField.contains(entry.getKey())){
                //排除当前字段
                continue;
            }
            if (exclude != null && exclude.contains(entry.getKey())){
                //排除当前字段
                continue;
            }
            Object o = entry.getValue();
            String value = String.valueOf(o);
            if(o instanceof List){
                List l = (List) o;
                if(null != l && l.size() >= 1){
                    Object oo = l.get(0);
                    value = JSONObject.toJSONString(oo);
                }
            }
            if (sb.toString().contains("="))
                sb.append("&");
            sb.append(entry.getKey() + "=" + value);
        }
        String signContent = sb.toString();
        return signContent;
    }
}
