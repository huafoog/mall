package com.qingshan.common.vo;

import lombok.Data;

/**
 * 公共分页参数
 */
@Data
public class BasePageInputVO {

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 当前页返回数据数量
     */
    private Integer limit;

    /**
     * 查询关键字
     */
    private String keywords;
}
