package com.DSDAAA.entity.product;

import com.DSDAAA.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "商品实体类")
public class Product extends BaseEntity {

	@Schema(description = "商品名称")
	private String name;					// 商品名称

	@Schema(description = "品牌id")
	private Long brandId;					// 品牌ID

	@Schema(description = "一级分类id")
	private Long category1Id;				// 一级分类id

	@Schema(description = "二级分类id")
	private Long category2Id;				// 二级分类id

	@Schema(description = "三级分类id")
	private Long category3Id;				// 三级分类id

	@Schema(description = "计量单位")
	private String unitName;				// 计量单位

	@Schema(description = "轮播图url")
	private String sliderUrls;				// 轮播图

	@Schema(description = "商品规格值json串")
	private String specValue;				// 商品规格值json串

	@Schema(description = "线上状态：0-初始值，1-上架，-1-自主下架")
	private Integer status;					// 线上状态：0-初始值，1-上架，-1-自主下架

	@Schema(description = "审核状态")
	private Integer auditStatus;			// 审核状态

	@Schema(description = "审核信息")
	private String auditMessage;			// 审核信息

	// 扩展的属性，用来封装响应的数据
	@Schema(description = "品牌名称")
	private String brandName;				// 品牌

	@Schema(description = "一级分类名称")
	private String category1Name;			// 一级分类

	@Schema(description = "二级分类名称")
	private String category2Name;			// 二级分类

	@Schema(description = "三级分类名称")
	private String category3Name;			// 三级分类

	@Schema(description = "sku列表集合")
	private List<ProductSku> productSkuList;		// sku列表集合

	@Schema(description = "图片详情列表")
	private String detailsImageUrls;				// 图片详情列表

	public Product() {
	}

	public Product(String name, Long brandId, Long category1Id, Long category2Id, Long category3Id, String unitName, String sliderUrls, String specValue, Integer status, Integer auditStatus, String auditMessage, String brandName, String category1Name, String category2Name, String category3Name, List<ProductSku> productSkuList, String detailsImageUrls) {
		this.name = name;
		this.brandId = brandId;
		this.category1Id = category1Id;
		this.category2Id = category2Id;
		this.category3Id = category3Id;
		this.unitName = unitName;
		this.sliderUrls = sliderUrls;
		this.specValue = specValue;
		this.status = status;
		this.auditStatus = auditStatus;
		this.auditMessage = auditMessage;
		this.brandName = brandName;
		this.category1Name = category1Name;
		this.category2Name = category2Name;
		this.category3Name = category3Name;
		this.productSkuList = productSkuList;
		this.detailsImageUrls = detailsImageUrls;
	}

	public String getName() {
		return name;
	}

	public Long getBrandId() {
		return brandId;
	}

	public Long getCategory1Id() {
		return category1Id;
	}

	public Long getCategory2Id() {
		return category2Id;
	}

	public Long getCategory3Id() {
		return category3Id;
	}

	public String getUnitName() {
		return unitName;
	}

	public String getSliderUrls() {
		return sliderUrls;
	}

	public String getSpecValue() {
		return specValue;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public String getAuditMessage() {
		return auditMessage;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getCategory1Name() {
		return category1Name;
	}

	public String getCategory2Name() {
		return category2Name;
	}

	public String getCategory3Name() {
		return category3Name;
	}

	public List<ProductSku> getProductSkuList() {
		return productSkuList;
	}

	public String getDetailsImageUrls() {
		return detailsImageUrls;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public void setCategory1Id(Long category1Id) {
		this.category1Id = category1Id;
	}

	public void setCategory2Id(Long category2Id) {
		this.category2Id = category2Id;
	}

	public void setCategory3Id(Long category3Id) {
		this.category3Id = category3Id;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public void setSliderUrls(String sliderUrls) {
		this.sliderUrls = sliderUrls;
	}

	public void setSpecValue(String specValue) {
		this.specValue = specValue;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setCategory1Name(String category1Name) {
		this.category1Name = category1Name;
	}

	public void setCategory2Name(String category2Name) {
		this.category2Name = category2Name;
	}

	public void setCategory3Name(String category3Name) {
		this.category3Name = category3Name;
	}

	public void setProductSkuList(List<ProductSku> productSkuList) {
		this.productSkuList = productSkuList;
	}

	public void setDetailsImageUrls(String detailsImageUrls) {
		this.detailsImageUrls = detailsImageUrls;
	}
}
