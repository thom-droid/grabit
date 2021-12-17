package com.cabbage.grabit.domain.product.dto.response;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductStat;
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
    private ProductStat productStat;

    public ProductResponseDto(Product entity) {
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.details = entity.getDetails();
        this.saleStatus = entity.isSaleStatus();
        this.productStat = entity.getProductStat();
    }
}
