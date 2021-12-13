package com.cabbage.grabit.domain.shipment.dto;

import com.cabbage.grabit.domain.shipment.ShippingAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingAddressResponseDto {

    private Long id;
    private String addressDetail;
    private String nickname;
    private String recipient;
    private boolean isDefault;

    public ShippingAddressResponseDto(ShippingAddress entity) {
        this.id = entity.getId();
        this.addressDetail = entity.getAddressDetail();
        this.nickname = entity.getNickname();
        this.recipient = entity.getRecipient();
        this.isDefault = entity.isDefault();
    }
}
