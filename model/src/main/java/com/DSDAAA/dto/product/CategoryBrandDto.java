package com.DSDAAA.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "搜索条件实体类")
public class CategoryBrandDto {

	@Schema(description = "品牌id")
	private Long brandId;

	@Schema(description = "分类id")
	private Long categoryId;

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}
