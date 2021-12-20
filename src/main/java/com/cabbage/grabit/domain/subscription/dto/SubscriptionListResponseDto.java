package com.cabbage.grabit.domain.subscription.dto;

import com.cabbage.grabit.domain.product.dto.response.ProductResponseDto;
import com.cabbage.grabit.domain.shipment.ShippingStatus;
import com.cabbage.grabit.domain.shipment.dto.ShippingAddressResponseDto;
import com.cabbage.grabit.domain.subscription.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.ClassInjector;

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

//    public SubscriptionListResponseDto(Subscription entity){
//        this.id = entity.getId();
//        this.isSubscribed = entity.getIsSubscribed();
//        this.shippingStatus = entity.getShippingStatus();
//        this.price = entity.getPrice();
//        this.shippingMessage = entity.getShippingMessage();
//        this.recipient = entity.getRecipient();
//        this.product = ProductResponseDto.from(entity.getProduct());
//        this.shippingAddress = new ShippingAddressResponseDto(entity.getShippingAddress());
//    }

    public static SubscriptionListResponseDto from(Subscription subscription){
        return SubscriptionListResponseDto.builder()
                .id(subscription.getId())
                .isSubscribed(subscription.getIsSubscribed())
                .shippingStatus(subscription.getShippingStatus())
                .price(subscription.getPrice())
                .shippingMessage(subscription.getShippingMessage())
                .recipient(subscription.getRecipient())
                .product(ProductResponseDto.from(subscription.getProduct()))
                .shippingAddress(ShippingAddressResponseDto.from(subscription.getShippingAddress()))
                .build();
    }
}
