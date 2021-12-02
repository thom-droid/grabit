package com.cabbage.grabit.web.dto.response;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductListResponseDto {

    private Long id;
    private String name;
    private Integer price;
    private String details;
    private boolean saleStatus;
    private BigDecimal subscriptionCount;
    private BigDecimal rate;
    private BigDecimal reviewCount;
    private LocalDateTime createdTime;
    private Category category;
    private ProductGiverResponseDto giver;

    public ProductListResponseDto(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.details = entity.getDetails();
        this.saleStatus = entity.isSaleStatus();
        this.subscriptionCount = entity.getProductStat().getSubscriptionCount();
        this.rate = entity.getProductStat().getRate();
        this.reviewCount = entity.getProductStat().getReviewCount();
        this.createdTime = entity.getCreatedDate();
        this.category = entity.getCategory();
        this.giver = new ProductGiverResponseDto(entity.getGiver());
    }

}
