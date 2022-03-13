package com.qingshan.mall.search.service;


import com.qingshan.mall.search.vo.SearchParam;
import com.qingshan.mall.search.vo.SearchResult;

public interface MallSearchService {

    /**
     * 搜索
     *
     * @param param 检索的所有参数
     * @return 返回检索的结果，里面包含页面需要的所有信息
     */
    SearchResult search(SearchParam param);
}
