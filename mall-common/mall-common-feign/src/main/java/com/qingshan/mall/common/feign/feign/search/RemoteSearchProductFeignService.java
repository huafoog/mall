package com.qingshan.mall.common.feign.feign.search;

import com.qingshan.common.core.constant.ProjectNameConstants;
import com.qingshan.common.core.to.es.SkuEsModel;
import com.qingshan.common.core.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@FeignClient(ProjectNameConstants.SEARCH)
public interface RemoteSearchProductFeignService {
    /**
     * 创建索引
     * @param models
     * @return
     */
    @PostMapping("/product/create")
    R CreateIndex(@RequestBody List<SkuEsModel> models);
}