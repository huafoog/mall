package com.qingshan.mall.search.service;

import com.qingshan.common.core.to.es.SkuEsModel;
import com.qingshan.common.core.utils.R;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    R createIndex(List<SkuEsModel> models) throws IOException;
}
