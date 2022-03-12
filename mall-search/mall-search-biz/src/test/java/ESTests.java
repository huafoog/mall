import com.alibaba.fastjson.JSON;
import com.qingshan.common.to.es.SkuEsModel;
import com.qingshan.mall.search.config.MallElasticSearchConfig;
import com.qingshan.mall.search.constant.EsConstants;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTests {

    /**
     *
     */
    @Autowired
    private RestHighLevelClient esClient;

    @Test
    public void createIndexTest() throws IOException {
        List<SkuEsModel> skuEsModels = new ArrayList<>();
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel model : skuEsModels) {
            IndexRequest indexRequest = new IndexRequest(EsConstants.PRODUCT_INDEX);
            indexRequest.id(model.getSkuId().toString());
            indexRequest.source(JSON.toJSONString(model), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = esClient.bulk(bulkRequest, MallElasticSearchConfig.COMMON_OPTIONS);
    }
}
