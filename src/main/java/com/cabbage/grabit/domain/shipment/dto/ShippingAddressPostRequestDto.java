package com.cabbage.grabit.domain.shipment.dto;

import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.user.Taker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingAddressPostRequestDto {

    private String addressDetail;
    private String nickname;
    private String recipient;
    private Boolean isDefault;
    private Region region;
    private Taker taker;

    public ShippingAddress toEntity(){
        return ShippingAddress.builder()
                .addressDetail(addressDetail)
                .nickname(nickname)
                .recipient(recipient)
                .isDefault(isDefault)
                .region(region)
                .taker(taker)
                .build();
    }

    public static ShippingAddressPostRequestDto toDto(ShippingAddress shippingAddress){
        return ShippingAddressPostRequestDto.builder()
                .addressDetail(shippingAddress.getAddressDetail())
                .nickname(shippingAddress.getNickname())
                .recipient(shippingAddress.getRecipient())
                .isDefault(shippingAddress.isDefault())
                .build();
    }

}
