package com.cabbage.grabit.domain.product.dto.response;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductStat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private Integer price;
    private String details;
    private boolean saleStatus;
//    private ProductStat productStat;

    public ProductResponseDto(Product entity) {
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.details = entity.getDetails();
        this.saleStatus = entity.isSaleStatus();
    }

    public static ProductResponseDto from(Product product){
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .details(product.getDetails())
                .saleStatus(product.isSaleStatus())
                .build();

    }
}
