package com.cabbage.grabit.domain.shipment;

import lombok.Getter;

@Getter
public enum ShippingStatus {

    PREPARING(1L, "준비중"),
    SHIPPING(2L, "배송중");

    Long code;
    String desc;

    ShippingStatus(Long code, String desc){
        this.code = code;
        this.desc = desc;
    }


}
