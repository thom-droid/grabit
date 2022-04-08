package com.cabbage.grabit.domain.product.dto.response;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductStat;
import lombok.*;

import java.time.LocalDateTime;
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductListResponseToGiverDto {

    private Long id;
    private String name;
    private Integer price;
    private String details;
    private boolean saleStatus;
    private LocalDateTime createdTime;
    private Category category;
    private ProductStat productStat;

//    public ProductListResponseToGiverDto(Product entity) {
//        this.id = entity.getId();
//        this.name = entity.getName();
//        this.price = entity.getPrice();
//        this.details = entity.getDetails();
//        this.saleStatus = entity.isSaleStatus();
//        this.productStat = entity.getProductStat();
//        this.createdTime = entity.getCreatedDate();
//        this.category = entity.getCategory();
//    }

    public static ProductListResponseToGiverDto from(Product product){
        return ProductListResponseToGiverDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .details(product.getDetails())
                .saleStatus(product.isSaleStatus())
                .createdTime(product.getCreatedDate())
                .category(product.getCategory())
                .productStat(product.getProductStat())
                .build();
    }
}
