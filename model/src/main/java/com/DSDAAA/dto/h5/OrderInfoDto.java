package com.DSDAAA.dto.h5;

import com.DSDAAA.entity.order.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

public class OrderInfoDto {

    //送货地址id
    private Long userAddressId;

    //运费
    private BigDecimal feightFee;

    //备注
    private String remark;

    //订单明细
    private List<OrderItem> orderItemList;

    public Long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Long userAddressId) {
        this.userAddressId = userAddressId;
    }

    public BigDecimal getFeightFee() {
        return feightFee;
    }

    public void setFeightFee(BigDecimal feightFee) {
        this.feightFee = feightFee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
