package com.qingshan.mall.search.feign;

import com.qingshan.common.constant.ProjectNameConstants;
import com.qingshan.common.to.es.SkuEsModel;
import com.qingshan.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 商品检索
 * @author qingshan
 */
@FeignClient(ProjectNameConstants.SEARCH)
public interface SearchProductFeignService {

    /**
     * 创建索引
     * @param models
     * @return
     */
    @PostMapping("/product/create")
    R CreateIndex(@RequestBody List<SkuEsModel> models);
}
