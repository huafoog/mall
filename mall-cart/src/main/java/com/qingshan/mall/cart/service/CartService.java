package com.qingshan.mall.cart.service;

import com.qingshan.mall.cart.vo.CartItemVO;
import com.qingshan.mall.cart.vo.CartVO;

import java.util.concurrent.ExecutionException;

public interface CartService {
    CartItemVO addToCart(Long skuId, Integer num)  throws ExecutionException, InterruptedException;

    CartItemVO getCartItem(Long skuId);

    CartVO getCart() throws ExecutionException, InterruptedException;

    void checkItem(Long skuId, Integer check);

    void changeItemCount(Long skuId, Integer num);

    void deleteItem(Long skuId);
}
