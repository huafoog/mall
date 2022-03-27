package com.qingshan.mall.cart.vo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车
 */
@Data
public class CartItemVO {

    private Long skuId;

    //是否选中
    private Boolean check = true;

    //标题
    private String title;

    //图片
    private String image;

    //商品套餐属性
    private List<String> skuAttr;

    //价格
    private BigDecimal price;

    //数量
    private Integer count;

    //总价
    private BigDecimal totalPrice;
    /**
     * 计算当前购物项总价
     * @return
     */
    public BigDecimal getTotalPrice() {
        return this.price.multiply(new BigDecimal(this.count));
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}