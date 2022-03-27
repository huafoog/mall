package com.qingshan.mall.ware.service.impl;

import com.qingshan.common.core.dto.product.sku.SkuInfoDTO;
import com.qingshan.common.core.dto.ware.WareSkuInfoDTO;
import com.qingshan.common.core.utils.R;
import com.qingshan.mall.common.feign.feign.product.RemoteProductFeignService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.common.core.utils.PageUtils;
import com.qingshan.common.core.utils.Query;

import com.qingshan.mall.ware.dao.WareSkuDao;
import com.qingshan.mall.ware.entity.WareSkuEntity;
import com.qingshan.mall.ware.service.WareSkuService;
import org.springframework.util.StringUtils;


@Service("wareSkuService")
@AllArgsConstructor
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    private final WareSkuDao wareSkuDao;

    private final RemoteProductFeignService productFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        /**
         * skuId: 1
         * wareId: 2
         */
        QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if(!StringUtils.isEmpty(skuId)){
            queryWrapper.eq("sku_id",skuId);
        }

        String wareId = (String) params.get("wareId");
        if(!StringUtils.isEmpty(wareId)){
            queryWrapper.eq("ware_id",wareId);
        }


        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //1、判断如果还没有这个库存记录新增
        List<WareSkuEntity> entities = wareSkuDao.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
        if(entities == null || entities.size() == 0){
            WareSkuEntity skuEntity = new WareSkuEntity();
            skuEntity.setSkuId(skuId);
            skuEntity.setStock(skuNum);
            skuEntity.setWareId(wareId);
            skuEntity.setStockLocked(0);
            //TODO 远程查询sku的名字，如果失败，整个事务无需回滚
            //1、自己catch异常
            //TODO 还可以用什么办法让异常出现以后不回滚？高级
            try {
                R<SkuInfoDTO> info = productFeignService.info(skuId);
                SkuInfoDTO data = info.getData();
                if(info.getCode() == 0){
                    skuEntity.setSkuName(data.getSkuName());
                }
            }catch (Exception e){

            }


            wareSkuDao.insert(skuEntity);
        }else{
            wareSkuDao.addStock(skuId,wareId,skuNum);
        }

    }

    /**
     * 根据skuid集合获取sku库存
     * @param skuIds
     * @return
     */
    @Override
    public List<WareSkuInfoDTO> getSkuStockBySkuIds(List<Long> skuIds) {

        List<WareSkuEntity> skuInfos = baseMapper.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", skuIds));

        return skuInfos.stream().map(sku->{
            WareSkuInfoDTO skuInfo = new WareSkuInfoDTO();
            skuInfo.setSkuId(sku.getSkuId());
            skuInfo.setHasStock(sku.getStock() > 0);
            return skuInfo;
        }).collect(Collectors.toList());
    }

}