package com.qingshan.mall.search.controller;

import com.qingshan.common.core.to.es.SkuEsModel;
import com.qingshan.common.core.utils.R;
import com.qingshan.mall.search.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 商品检索
 * @author qingshan
 */
@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    /**
     * 创建索引
     * @param models
     * @return
     */
    @PostMapping("/create")
    public R CreateIndex(@RequestBody List<SkuEsModel> models) throws IOException {
        return productService.createIndex(models);
    }
}
