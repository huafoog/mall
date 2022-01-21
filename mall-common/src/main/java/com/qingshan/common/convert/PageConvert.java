package com.qingshan.common.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshan.common.vo.BasePageInputVO;

/**
 * Page转换类
 * @author qingshan
 */
public class PageConvert {
    /**
     * 转换为page
     * @param query
     * @return
     */
    public static Page toPage(BasePageInputVO query){
        return new Page(query.getPage(),query.getLimit());
    }
}
