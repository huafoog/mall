package com.qingshan.mall.product.service;

<<<<<<< HEAD:mall-product/mall-product-biz/src/main/java/com/qingshan/mall/product/service/CategoryBrandRelationService.java
import com.qingshan.mall.product.entity.BrandEntity;
import com.baomidou.mybatisplus.extension.service.IService;
=======
>>>>>>> dbd7be12265b119c9fda2dc7e617be6820ea0825:mall-product/src/main/java/com/qingshan/mall/product/service/CategoryBrandRelationService.java
import com.qingshan.common.utils.PageUtils;
import com.qingshan.mall.product.entity.BrandEntity;
import com.qingshan.mall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-11-17 21:25:25
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    void updateBrand(Long brandId, String name);

    void updateCategory(Long catId, String name);

    List<BrandEntity> getBrandsByCatId(Long catId);

}

