package com.qingshan.mall.cart.service;

import com.qingshan.mall.cart.vo.CartItemVO;

import java.util.concurrent.ExecutionException;

public interface CartService {
    CartItemVO addToCart(Long skuId, Integer num)  throws ExecutionException, InterruptedException;
}
