package com.cabbage.grabit.domain.product.dto;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductStat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductListResponseDto {

    private Long id;
    private String name;
    private Integer price;
    private String details;
    private boolean saleStatus;
    private ProductStat productStat;
    private LocalDateTime createdTime;
    private Category category;
    private ProductGiverResponseDto giver;

    public ProductListResponseDto(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.details = entity.getDetails();
        this.saleStatus = entity.isSaleStatus();
        this.productStat = entity.getProductStat();
        this.createdTime = entity.getCreatedDate();
        this.category = entity.getCategory();
        this.giver = new ProductGiverResponseDto(entity.getGiver());
    }

}
