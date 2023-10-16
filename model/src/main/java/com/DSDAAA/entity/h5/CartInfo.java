package com.DSDAAA.entity.h5;

import com.DSDAAA.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "购物车实体类")
public class CartInfo extends BaseEntity {

   private static final long serialVersionUID = 1L;

   @Schema(description = "用户id")
   private Long userId;

   @Schema(description = "skuid")
   private Long skuId;

   @Schema(description = "放入购物车时价格")
   private BigDecimal cartPrice;

   @Schema(description = "数量")
   private Integer skuNum;

   @Schema(description = "图片文件")
   private String imgUrl;

   @Schema(description = "sku名称 (冗余)")
   private String skuName;

   @Schema(description = "isChecked")
   private Integer isChecked;

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Long getSkuId() {
      return skuId;
   }

   public void setSkuId(Long skuId) {
      this.skuId = skuId;
   }

   public BigDecimal getCartPrice() {
      return cartPrice;
   }

   public void setCartPrice(BigDecimal cartPrice) {
      this.cartPrice = cartPrice;
   }

   public Integer getSkuNum() {
      return skuNum;
   }

   public void setSkuNum(Integer skuNum) {
      this.skuNum = skuNum;
   }

   public String getImgUrl() {
      return imgUrl;
   }

   public void setImgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
   }

   public String getSkuName() {
      return skuName;
   }

   public void setSkuName(String skuName) {
      this.skuName = skuName;
   }

   public Integer getIsChecked() {
      return isChecked;
   }

   public void setIsChecked(Integer isChecked) {
      this.isChecked = isChecked;
   }
}
