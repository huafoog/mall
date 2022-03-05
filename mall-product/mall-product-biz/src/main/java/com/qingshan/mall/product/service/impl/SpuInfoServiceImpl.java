package com.qingshan.mall.product.service.impl;

import com.qingshan.common.constant.ProductConstants;
import com.qingshan.common.to.SkuReductionTo;
import com.qingshan.common.to.SpuBoundTo;
import com.qingshan.common.to.es.SkuEsModel;
import com.qingshan.common.utils.R;
import com.qingshan.mall.coupon.feign.CouponFeignService;
import com.qingshan.mall.product.dao.BrandDao;
import com.qingshan.mall.product.dao.CategoryDao;
import com.qingshan.mall.product.entity.*;
import com.qingshan.mall.product.service.*;
import com.qingshan.mall.product.vo.*;
import com.qingshan.mall.search.feign.SearchProductFeignService;
import com.qingshan.mall.ware.dto.WareSkuInfoDTO;
import com.qingshan.mall.ware.feign.WareSkuFeignService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.common.utils.PageUtils;
import com.qingshan.common.utils.Query;

import com.qingshan.mall.product.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("spuInfoService")
@AllArgsConstructor
@Slf4j
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    private final SpuInfoDescService spuInfoDescService;

    private final SpuImagesService imagesService;

    private final AttrService attrService;

    private final ProductAttrValueService attrValueService;

    private final SkuInfoService skuInfoService;
    private final SkuImagesService skuImagesService;

    private final SkuSaleAttrValueService skuSaleAttrValueService;

    private final CouponFeignService couponFeignService;

    private final CategoryDao categoryDao;


    private final BrandDao brandDao;

    private final WareSkuFeignService wareSkuFeignService;
    
    private final SearchProductFeignService searchProductFeignService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * //TODO 高级部分完善
     * @param vo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveSpuInfo(SpuSaveVo vo) {

        //1、保存spu基本信息 pms_spu_info
        SpuInfoEntity infoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(vo,infoEntity);
        infoEntity.setCreateTime(new Date());
        infoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(infoEntity);

        //2、保存Spu的描述图片 pms_spu_info_desc
        List<String> decript = vo.getDecript();
        SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
        descEntity.setSpuId(infoEntity.getId());
        descEntity.setDecript(String.join(",",decript));
        spuInfoDescService.saveSpuInfoDesc(descEntity);



        //3、保存spu的图片集 pms_spu_images
        List<String> images = vo.getImages();
        imagesService.saveImages(infoEntity.getId(),images);


        //4、保存spu的规格参数;pms_product_attr_value
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValueEntity> collect = baseAttrs.stream().map(attr -> {
            ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
            valueEntity.setAttrId(attr.getAttrId());
            AttrEntity id = attrService.getById(attr.getAttrId());
            valueEntity.setAttrName(id.getAttrName());
            valueEntity.setAttrValue(attr.getAttrValues());
            valueEntity.setQuickShow(attr.getShowDesc());
            valueEntity.setSpuId(infoEntity.getId());

            return valueEntity;
        }).collect(Collectors.toList());
        attrValueService.saveProductAttr(collect);


        //5、保存spu的积分信息；mall_sms->sms_spu_bounds
        Bounds bounds = vo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds,spuBoundTo);
        spuBoundTo.setSpuId(infoEntity.getId());
        R r = couponFeignService.saveSpuBounds(spuBoundTo);
        if(r.getCode() != 0){
            log.error("远程保存spu积分信息失败");
        }


        //5、保存当前spu对应的所有sku信息；

        List<Skus> skus = vo.getSkus();
        if(skus!=null && skus.size()>0){
            skus.forEach(item->{
                String defaultImg = "";
                for (Images image : item.getImages()) {
                    if(image.getDefaultImg() == 1){
                        defaultImg = image.getImgUrl();
                    }
                }
                //    private String skuName;
                //    private BigDecimal price;
                //    private String skuTitle;
                //    private String skuSubtitle;
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item,skuInfoEntity);
                skuInfoEntity.setBrandId(infoEntity.getBrandId());
                skuInfoEntity.setCatalogId(infoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(infoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                //5.1）、sku的基本信息；pms_sku_info
                skuInfoService.saveSkuInfo(skuInfoEntity);

                Long skuId = skuInfoEntity.getSkuId();

                List<SkuImagesEntity> imagesEntities = item.getImages().stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                    return skuImagesEntity;
                }).filter(entity->{
                    //返回true就是需要，false就是剔除
                    return !StringUtils.isEmpty(entity.getImgUrl());
                }).collect(Collectors.toList());
                //5.2）、sku的图片信息；pms_sku_image
                skuImagesService.saveBatch(imagesEntities);
                //TODO 没有图片路径的无需保存

                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(a -> {
                    SkuSaleAttrValueEntity attrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(a, attrValueEntity);
                    attrValueEntity.setSkuId(skuId);

                    return attrValueEntity;
                }).collect(Collectors.toList());
                //5.3）、sku的销售属性信息：pms_sku_sale_attr_value
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

                // //5.4）、sku的优惠、满减等信息；mall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(item,skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                if(skuReductionTo.getFullCount() >0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) == 1){
                    R r1 = couponFeignService.saveSkuReduction(skuReductionTo);
                    if(r1.getCode() != 0){
                        log.error("远程保存sku优惠信息失败");
                    }
                }



            });
        }






    }

    @Override
    public void saveBaseSpuInfo(SpuInfoEntity infoEntity) {
        this.baseMapper.insert(infoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {

        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wrapper.and((w)->{
                w.eq("id",key).or().like("spu_name",key);
            });
        }
        // status=1 and (id=1 or spu_name like xxx)
        String status = (String) params.get("status");
        if(!StringUtils.isEmpty(status)){
            wrapper.eq("publish_status",status);
        }

        String brandId = (String) params.get("brandId");
        if(!StringUtils.isEmpty(brandId)&&!"0".equalsIgnoreCase(brandId)){
            wrapper.eq("brand_id",brandId);
        }

        String catelogId = (String) params.get("catelogId");
        if(!StringUtils.isEmpty(catelogId)&&!"0".equalsIgnoreCase(catelogId)){
            wrapper.eq("catalog_id",catelogId);
        }

        /**
         * status: 2
         * key:
         * brandId: 9
         * catelogId: 225
         */

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public R spuUp(Long spuId) {

        // 获取sku集合
        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<SkuInfoEntity>().eq("spu_id", spuId);
        List<SkuInfoEntity> skuList = skuInfoService.list(queryWrapper);

        SpuInfoEntity spuInfo = baseMapper.selectById(spuId);
        BrandEntity brand = brandDao.selectById(spuInfo.getBrandId());
        CategoryEntity category = categoryDao.selectById(spuInfo.getCatalogId());


        // 当前商品的所有attr值
        List<ProductAttrValueEntity> productAttrValueEntities = attrValueService.baseAttrlistforspu(spuId);
        // attr id集合
        List<Long> attrIds = productAttrValueEntities.stream().map(o -> o.getAttrId()).collect(Collectors.toList());
        List<Long> searchAttrIds =  attrService.selectSearchAttrIds(attrIds);
        List<SkuEsModel.Attrs> attrs = productAttrValueEntities.stream().filter(item -> searchAttrIds.contains(item.getAttrId())).map(item -> {
            SkuEsModel.Attrs attr = new SkuEsModel.Attrs();
            attr.setAttrId(item.getAttrId());
            attr.setAttrName(item.getAttrName());
            attr.setAttrValue(item.getAttrValue());
            return attr;
        }).collect(Collectors.toList());


        List<Long> skuIds = skuList.stream().map(o -> o.getSkuId()).collect(Collectors.toList());

        List<WareSkuInfoDTO> skuStocks = null;
        try{
            R<List<WareSkuInfoDTO>> skuStockBySkuIds = wareSkuFeignService.getSkuStockBySkuIds(skuIds);
            if (skuStockBySkuIds.getCode() == 0){
                skuStocks = skuStockBySkuIds.getData();
            }
        }catch (Exception ex){
            log.error("发生错误",ex);
        }
        List<WareSkuInfoDTO> stocks = skuStocks;
        List<SkuEsModel> collect = skuList.stream().map(sku -> {
            SkuEsModel esModel = new SkuEsModel();

            BeanUtils.copyProperties(sku,esModel);


            esModel.setSkuPrice(sku.getPrice());
            esModel.setSkuImg(sku.getSkuDefaultImg());


            if (stocks == null){
                esModel.setHasStock(true);
            }else{
                for (WareSkuInfoDTO stock : stocks) {
                    if (stock.getSkuId().equals(sku.getSkuId())){
                        esModel.setHasStock(stock.getHasStock());
                    }else{
                        esModel.setHasStock(true);
                    }
                }
            }

            esModel.setHotScore(0L);

            esModel.setBrandId(brand.getBrandId());
            esModel.setBrandImg(brand.getLogo());
            esModel.setBrandName(brand.getName());

            esModel.setCatalogId(category.getCatId());
            esModel.setCatalogName(category.getName());
            esModel.setAttrs(attrs);


            return esModel;
        }).collect(Collectors.toList());

        R r = searchProductFeignService.CreateIndex(collect);
        if (r.getCode() == 0){
            //操作成功
            SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
            spuInfoEntity.setId(spuId);
            spuInfoEntity.setPublishStatus(ProductConstants.PublishStatusEnum.UP.getCode());
            spuInfoEntity.setUpdateTime(new Date());
            //修改spu上架
            updateById(spuInfoEntity);
        }
        return r;
    }


}