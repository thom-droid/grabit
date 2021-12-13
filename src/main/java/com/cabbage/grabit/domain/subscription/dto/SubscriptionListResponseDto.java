package com.cabbage.grabit.domain.subscription.dto;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.dto.ProductResponseDto;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.shipment.ShippingStatus;
import com.cabbage.grabit.domain.shipment.dto.ShippingAddressResponseDto;
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

    private Long id;
    private boolean isSubscribed;
    private ShippingStatus shippingStatus;
    private Integer price;
    private String shippingMessage;
    private String recipient;
    private ProductResponseDto product;
    private ShippingAddressResponseDto shippingAddress;

    public SubscriptionListResponseDto(Subscription entity){
        this.id = entity.getId();
        this.isSubscribed = entity.getIsSubscribed();
        this.shippingStatus = entity.getShippingStatus();
        this.price = entity.getPrice();
        this.shippingMessage = entity.getShippingMessage();
        this.recipient = entity.getRecipient();
        this.product = new ProductResponseDto(entity.getProduct());
        this.shippingAddress = new ShippingAddressResponseDto(entity.getShippingAddress());
    }
}
