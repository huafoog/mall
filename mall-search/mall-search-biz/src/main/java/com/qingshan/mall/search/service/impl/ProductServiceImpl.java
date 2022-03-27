package com.qingshan.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.qingshan.common.core.to.es.SkuEsModel;
import com.qingshan.common.core.utils.R;
import com.qingshan.mall.search.config.MallElasticSearchConfig;
import com.qingshan.mall.search.constant.EsConstants;
import com.qingshan.mall.search.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final RestHighLevelClient esClient;

    /**
     * 创建索引
     * @param models
     * @return
     * @throws IOException
     */
    @Override
    public R createIndex(List<SkuEsModel> models) throws IOException {
        if (models == null || models.size() == 0){
            return R.failed("未获取到需要创建的数据");
        }
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel model : models) {
            IndexRequest indexRequest = new IndexRequest(EsConstants.PRODUCT_INDEX);
            indexRequest.id(model.getSkuId().toString());
            indexRequest.source(JSON.toJSONString(model), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = esClient.bulk(bulkRequest, MallElasticSearchConfig.COMMON_OPTIONS);
        // 處理錯誤信息
        if (bulkResponse.hasFailures()) {
            log.warn("====================批量創建索引過程中出現錯誤 下麵是錯誤信息==========================");
            long count = 0L;
            StringBuilder sb = new StringBuilder();
            sb.append("创建索引出现错误，sku【");
            for (BulkItemResponse bulkItemResponse : bulkResponse) {
                log.warn("索引 " + EsConstants.PRODUCT_INDEX + " 發生錯誤的 索引id為 : " + bulkItemResponse.getId() + " ，錯誤信息為：" + bulkItemResponse.getFailureMessage());
                count++;
                String message = bulkItemResponse.getId();
                if (count == 0L)
                {
                    sb.append(message);
                }else{
                    sb.append(","+message);
                }
            }
            sb.append("】插入失败，具体原因请查看日志");
            log.warn("====================批量創建索引過程中出現錯誤 上麵是錯誤信息 共有: " + count + " 條記錄==========================");
            return R.failed(sb.toString());

        }
        return R.ok();
    }
}
