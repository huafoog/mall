package com.qingshan.mall.product.service.impl;

<<<<<<< HEAD:mall-product/mall-product-biz/src/main/java/com/qingshan/mall/product/service/impl/AttrAttrgroupRelationServiceImpl.java
import com.qingshan.mall.product.vo.AttrGroupRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

=======
>>>>>>> dbd7be12265b119c9fda2dc7e617be6820ea0825:mall-product/src/main/java/com/qingshan/mall/product/service/impl/AttrAttrgroupRelationServiceImpl.java
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.mall.product.dao.AttrAttrgroupRelationDao;
import com.qingshan.mall.product.entity.AttrAttrgroupRelationEntity;
import com.qingshan.mall.product.service.AttrAttrgroupRelationService;
import com.qingshan.mall.product.vo.AttrGroupRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.qingshan.common.utils.PageUtils;
import com.qingshan.common.utils.Query;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveBatch(List<AttrGroupRelationVo> vos) {
        List<AttrAttrgroupRelationEntity> collect = vos.stream().map(item -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }

}