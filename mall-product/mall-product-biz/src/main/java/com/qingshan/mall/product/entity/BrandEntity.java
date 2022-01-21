package com.qingshan.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
<<<<<<< HEAD:mall-product/mall-product-biz/src/main/java/com/qingshan/mall/product/entity/BrandEntity.java

import java.io.Serializable;

=======
>>>>>>> dbd7be12265b119c9fda2dc7e617be6820ea0825:mall-product/src/main/java/com/qingshan/mall/product/entity/BrandEntity.java
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
<<<<<<< HEAD:mall-product/mall-product-biz/src/main/java/com/qingshan/mall/product/entity/BrandEntity.java
=======
import java.io.Serializable;
>>>>>>> dbd7be12265b119c9fda2dc7e617be6820ea0825:mall-product/src/main/java/com/qingshan/mall/product/entity/BrandEntity.java

/**
 * 品牌
 * 
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@NotNull(message = "修改必须指定品牌id")
	@Null(message = "新增不能指定id")
	@TableId
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名必须提交")
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank
	@URL(message = "logo必须是一个合法的url地址")
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
//	@Pattern()
<<<<<<< HEAD:mall-product/mall-product-biz/src/main/java/com/qingshan/mall/product/entity/BrandEntity.java
=======
	@NotNull
>>>>>>> dbd7be12265b119c9fda2dc7e617be6820ea0825:mall-product/src/main/java/com/qingshan/mall/product/entity/BrandEntity.java
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z]$",message = "检索首字母必须是一个字母")
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull
	@Min(value = 0,message = "排序必须大于等于0")
	private Integer sort;

}
