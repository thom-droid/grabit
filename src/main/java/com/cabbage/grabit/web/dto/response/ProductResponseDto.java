package com.cabbage.grabit.web.dto.response;

import com.cabbage.grabit.domain.products.Products;
import com.cabbage.grabit.domain.user.Giver;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private String name;
    private Integer price;
    private String details;
    private boolean saleStatus;
    private BigDecimal subscriptionCount;
    private BigDecimal rate;
    private BigDecimal reviewCount;

    public ProductResponseDto(Products entity) {
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.details = entity.getDetails();
        this.saleStatus = entity.isSaleStatus();
        this.subscriptionCount = entity.getSubscriptionCount();
        this.rate = entity.getRate();
        this.reviewCount = entity.getReviewCount();
    }
}
