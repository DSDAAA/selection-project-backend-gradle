package com.DSDAAA.dto.product;

import lombok.Data;

public class SkuSaleDto {

	private Long skuId;
	private Integer num;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}

