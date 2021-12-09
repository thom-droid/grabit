package com.cabbage.grabit.domain.shipment.dto;

import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingAddressListToTakerResponseDto {

    Long id;
    String addressDetail;
    String nickname;
    String recipient;
    boolean isDefault;
    Region region;


    public ShippingAddressListToTakerResponseDto(ShippingAddress entity) {
        this.id = entity.getId();
        this.addressDetail = entity.getAddressDetail();
        this.nickname = entity.getNickname();
        this.recipient = entity.getRecipient();
        this.isDefault = entity.isDefault();
        this.region = entity.getRegion();
    }
}
