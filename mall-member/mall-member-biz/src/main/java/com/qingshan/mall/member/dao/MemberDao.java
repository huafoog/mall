package com.qingshan.mall.member.dao;

import com.qingshan.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author qingshan
 * @email zyxss315@163.com
 * @date 2022-03-18 16:22:26
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
