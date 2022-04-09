package com.qingshan.mall.cart.web;

import com.qingshan.mall.cart.dto.UserInfoDTO;
import com.qingshan.mall.cart.interceptor.CartInterceptor;
import com.qingshan.mall.cart.service.CartService;
import com.qingshan.mall.cart.vo.CartItemVO;
import com.qingshan.mall.cart.vo.CartVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//    /**
//     * 添加商品到购物车
//     * @return
//     */
//    @GetMapping("/addCartItem")
//    public String addCartItem(@RequestParam("skuId") Long skuId,
//                            @RequestParam("num") Integer num,
//                            Model model) throws ExecutionException, InterruptedException {
//        CartItemVO cartItem = cartService.addToCart(skuId,num);
//        System.out.println(cartItem.toString());
//        model.addAttribute("item",cartItem);
//        return "success";
//    }

    /**
     * 添加商品到购物车
     * RedirectAttributes attributes
     * attributes.addFlashAttribute();将数据放在session里面可以在页面取出，但只能取一次
     * attributes.addAttribute("skuId",skuId); 将数据放在url后面
     * @return
     */
    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("skuId") Long skuId,
                            @RequestParam("num") Integer num,
                            RedirectAttributes attributes) throws ExecutionException, InterruptedException {
        cartService.addToCart(skuId,num);
        attributes.addAttribute("skuId",skuId);

        return "redirect:http://cart.mall.com/addToCartSuccess.html";
    }

    /**
     * 跳转到成功页
     * @param skuId
     * @param model
     * @return
     */
    @GetMapping("/addToCartSuccess.html")
    public String addToCartSuccessPage(@RequestParam("skuId") Long skuId,Model model){
        CartItemVO cartItem = cartService.getCartItem(skuId);
        System.out.println(cartItem.toString());
        model.addAttribute("item",cartItem);
        return "success";
    }

    @GetMapping("/cart.html")
    public String cartListPage(Model model) throws ExecutionException, InterruptedException {

        //快速得到用户信息，id,user-key
//        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        CartVO cart = cartService.getCart();
        model.addAttribute("cart",cart);
        return "cartList";
    }

    @GetMapping("/checkItem")
    public String checkItem(@RequestParam("skuId") Long skuId, @RequestParam("check") Integer check){
        cartService.checkItem(skuId,check);
        return "redirect:http://cart.mall.com/cart.html";
    }


    @GetMapping("/changeItemCount")
    public String changeItemCount(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num){
        cartService.changeItemCount(skuId,num);
        return "redirect:http://cart.mall.com/cart.html";
    }

    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("skuId") Long skuId){
        cartService.deleteItem(skuId);
        return "redirect:http://cart.mall.com/cart.html";
    }
}
