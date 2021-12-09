package com.cabbage.grabit.domain.subscription.dto;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.shipment.ShippingStatus;
import com.cabbage.grabit.domain.subscription.Subscription;
import com.cabbage.grabit.domain.user.Taker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionListResponseDto {

    private boolean isSubscribed;
    private ShippingStatus shippingStatus;
    private Integer price;
    private String shippingMessage;
    private String recipient;
    private Taker taker;
    private Product product;
    private ShippingAddress shippingAddress;

    public SubscriptionListResponseDto(Subscription entity){
        SubscriptionListResponseDto.builder()
                .isSubscribed(entity.isSubscribed())
                .shippingStatus(entity.getShippingStatus())
                .price(entity.getPrice())
                .shippingMessage(entity.getShippingMessage())
                .recipient(entity.getRecipient())
                .taker(entity.getTaker())
                .product(entity.getProduct())
                .shippingAddress(entity.getShippingAddress())
                .build();
    }
}
