package com.qingshan.mall.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.client.utils.StringUtils;
import com.qingshan.common.core.dto.product.sku.SkuInfoDTO;
import com.qingshan.common.core.utils.R;
import com.qingshan.mall.cart.dto.UserInfoDTO;
import com.qingshan.mall.cart.interceptor.CartInterceptor;
import com.qingshan.mall.cart.service.CartService;
import com.qingshan.mall.cart.vo.CartItemVO;
import com.qingshan.mall.common.feign.feign.product.RemoteProductFeignService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final String CART_PREFIX = "gulimall:cart";
    private final StringRedisTemplate redisTemplate;
    private final RemoteProductFeignService productFeignService;

    ThreadPoolExecutor executor;
    @Override
    public CartItemVO addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();

        String res = (String) cartOps.get(skuId.toString());
        // 1、添加新商品到购物车（购物车无此商品）
        if (StringUtils.isEmpty(res)){
            CartItemVO cartItem = new CartItemVO();

            /**
             * 异步查询
             */
            CompletableFuture<Void> getSkuInfo = CompletableFuture.runAsync(() -> {
                //1.1、远程查询要添加的商品信息
                R<SkuInfoDTO> skuInfo = productFeignService.getSkuInfo(skuId);
                if (skuInfo.isSuccess()){
                    SkuInfoDTO data = skuInfo.getData();
                    cartItem.setCheck(true);
                    cartItem.setCount(1);
                    cartItem.setImage(data.getSkuDefaultImg());
                    cartItem.setTitle(data.getSkuTitle());
                    cartItem.setSkuId(skuId);
                    cartItem.setPrice(data.getPrice());
                }
            },executor);
            CompletableFuture<Void> getSkuSaleAttrValues = CompletableFuture.runAsync(() -> {
                R<List<String>> skuSaleAttrValues = productFeignService.getSkuSaleAttrValues(skuId);
                if (skuSaleAttrValues.isSuccess()){
                    //1.2、远程查询sku的组合信息
                    List<String> values = skuSaleAttrValues.getData();
                    cartItem.setSkuAttr(values);
                }
            }, executor);
            CompletableFuture.allOf(getSkuInfo,getSkuSaleAttrValues).get();
            String jsonString = JSON.toJSONString(cartItem);
            cartOps.put(skuId.toString(),jsonString);
            return cartItem;
        }else {
            //2、购物车有此商品，将数据取出修改数量即可
            CartItemVO cartItem = JSON.parseObject(res, CartItemVO.class);
            cartItem.setCount(cartItem.getCount() + num);
            cartOps.put(skuId.toString(),JSON.toJSONString(cartItem));
            return cartItem;
        }
    }

    /**
     * 获取我们要操作的购物车，临时购物车、用户购物车
     * @return
     */
    private BoundHashOperations<String, Object, Object> getCartOps() {
        //得到用户信息 账号用户 、临时用户
        UserInfoDTO userInfoTo = CartInterceptor.threadLocal.get();
        //1、userInfoTo.getUserId()不为空表示账号用户，反之临时用户  然后决定用临时购物车还是用户购物车
        //放入缓存的key
        String cartKey = "";
        if (userInfoTo.getUserId() != null){
            cartKey = CART_PREFIX + userInfoTo.getUserId();
        }else {
            cartKey = CART_PREFIX + userInfoTo.getUserKey();
        }
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(cartKey);
        return operations;
    }
}
