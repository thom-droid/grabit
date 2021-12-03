package com.cabbage.grabit.domain.product.dto;

import com.cabbage.grabit.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public ProductResponseDto(Product entity) {
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.details = entity.getDetails();
        this.saleStatus = entity.isSaleStatus();
        this.subscriptionCount = entity.getProductStat().getSubscriptionCount();
        this.rate = entity.getProductStat().getRate();
        this.reviewCount = entity.getProductStat().getReviewCount();
    }
}
