package com.cabbage.grabit.domain.shipment.dto;

import com.cabbage.grabit.domain.shipment.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public static ShippingAddressResponseDto from(ShippingAddress shippingAddress){
        return ShippingAddressResponseDto.builder()
                .id(shippingAddress.getId())
                .addressDetail(shippingAddress.getAddressDetail())
                .nickname(shippingAddress.getNickname())
                .recipient(shippingAddress.getRecipient())
                .isDefault(shippingAddress.isDefault())
                .build();
    }
}
