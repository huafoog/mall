package com.qingshan.mall.product.service.impl;

<<<<<<< HEAD:mall-product/mall-product-biz/src/main/java/com/qingshan/mall/product/service/impl/ProductAttrValueServiceImpl.java
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

=======
import com.qingshan.common.utils.PageUtils;
import com.qingshan.common.utils.Query;
import com.qingshan.mall.product.entity.ProductAttrValueEntity;
import com.qingshan.mall.product.service.ProductAttrValueService;
>>>>>>> dbd7be12265b119c9fda2dc7e617be6820ea0825:mall-product/src/main/java/com/qingshan/mall/product/service/impl/ProductAttrValueServiceImpl.java
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.mall.product.dao.ProductAttrValueDao;
<<<<<<< HEAD:mall-product/mall-product-biz/src/main/java/com/qingshan/mall/product/service/impl/ProductAttrValueServiceImpl.java
import com.qingshan.mall.product.entity.ProductAttrValueEntity;
import com.qingshan.mall.product.service.ProductAttrValueService;
import org.springframework.transaction.annotation.Transactional;
=======
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
>>>>>>> dbd7be12265b119c9fda2dc7e617be6820ea0825:mall-product/src/main/java/com/qingshan/mall/product/service/impl/ProductAttrValueServiceImpl.java


@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<ProductAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveProductAttr(List<ProductAttrValueEntity> collect) {
        this.saveBatch(collect);
    }

    @Override
    public List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId) {
        List<ProductAttrValueEntity> entities = this.baseMapper.selectList(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", spuId));
        return entities;
    }

    @Transactional
    @Override
    public void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities) {
        //1、删除这个spuId之前对应的所有属性
        this.baseMapper.delete(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id",spuId));


        List<ProductAttrValueEntity> collect = entities.stream().map(item -> {
            item.setSpuId(spuId);
            return item;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }

}