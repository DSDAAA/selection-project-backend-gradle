package com.DSDAAA.vo.product;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CategoryExcelVo {

	@ExcelProperty(value = "id" ,index = 0)
	private Long id;

	@ExcelProperty(value = "名称" ,index = 1)
	private String name;

	@ExcelProperty(value = "图片url" ,index = 2)
	private String imageUrl ;

	@ExcelProperty(value = "上级id" ,index = 3)
	private Long parentId;

	@ExcelProperty(value = "状态" ,index = 4)
	private Integer status;

	@ExcelProperty(value = "排序" ,index = 5)
	private Integer orderNum;

	public CategoryExcelVo() {
	}

	public CategoryExcelVo(Long id, String name, String imageUrl, Long parentId, Integer status, Integer orderNum) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.parentId = parentId;
		this.status = status;
		this.orderNum = orderNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
}
