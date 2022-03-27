package com.qingshan.mall.cart.web;

import com.qingshan.mall.cart.dto.UserInfoDTO;
import com.qingshan.mall.cart.interceptor.CartInterceptor;
import com.qingshan.mall.cart.service.CartService;
import com.qingshan.mall.cart.vo.CartItemVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@Controller
@AllArgsConstructor
public class IndexController {

    private final CartService cartService;

    @GetMapping("/")
    public String Index(){
        //快速得到用户信息，id,user-key
        UserInfoDTO userInfoTo = CartInterceptor.threadLocal.get();
        System.out.println(userInfoTo);
        return "cartList";
    }
    /**
     * 添加商品到购物车
     * @return
     */
    @GetMapping("/addCartItem")
    public String addCartItem(@RequestParam("skuId") Long skuId,
                            @RequestParam("num") Integer num,
                            Model model) throws ExecutionException, InterruptedException {
        CartItemVO cartItem = cartService.addToCart(skuId,num);
        model.addAttribute("item",cartItem);
        return "success";
    }
}
