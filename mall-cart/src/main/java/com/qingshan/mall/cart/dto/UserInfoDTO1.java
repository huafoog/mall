package com.qingshan.mall.cart.dto;

import lombok.Data;

@Data
public class UserInfoDTO1 {
    private Long userId;

    private String userKey; //一定封装

    private boolean tempUser = false;  //判断是否有临时用户
}
