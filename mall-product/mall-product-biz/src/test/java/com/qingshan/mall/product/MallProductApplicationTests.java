package com.qingshan.mall.product;

import com.qingshan.mall.product.entity.BrandEntity;
import com.qingshan.mall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
class MallProductApplicationTests {

    /**
     *
     */
    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {
        BrandEntity model = new BrandEntity();
        model.setDescript("华为");
        brandService.save(model);
        System.out.println("success");
    }

}
