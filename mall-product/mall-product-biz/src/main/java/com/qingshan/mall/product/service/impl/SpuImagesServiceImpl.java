package com.qingshan.mall.product.service.impl;

<<<<<<< HEAD:mall-product/mall-product-biz/src/main/java/com/qingshan/mall/product/service/impl/SpuImagesServiceImpl.java
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

=======
import com.qingshan.common.utils.PageUtils;
import com.qingshan.common.utils.Query;
import com.qingshan.mall.product.entity.SpuImagesEntity;
import com.qingshan.mall.product.service.SpuImagesService;
>>>>>>> dbd7be12265b119c9fda2dc7e617be6820ea0825:mall-product/src/main/java/com/qingshan/mall/product/service/impl/SpuImagesServiceImpl.java
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.mall.product.dao.SpuImagesDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuImagesEntity> page = this.page(
                new Query<SpuImagesEntity>().getPage(params),
                new QueryWrapper<SpuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveImages(Long id, List<String> images) {
        if(images == null || images.size() == 0){

        }else{
            List<SpuImagesEntity> collect = images.stream().map(img -> {
                SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
                spuImagesEntity.setSpuId(id);
                spuImagesEntity.setImgUrl(img);

                return spuImagesEntity;
            }).collect(Collectors.toList());

            this.saveBatch(collect);
        }
    }

}