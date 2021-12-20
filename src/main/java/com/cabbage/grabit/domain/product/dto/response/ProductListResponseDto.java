package com.cabbage.grabit.domain.product.dto.response;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductStat;
import com.cabbage.grabit.domain.user.dto.response.GiverResponseForProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductListResponseDto {

    private Long id;
    private String name;
    private Integer price;
    private String details;
    private boolean saleStatus;
    private ProductStat productStat;
    private LocalDateTime createdTime;
    private Category category;
    private GiverResponseForProduct giver;

//    public ProductListResponseDto(Product entity) {
//        this.id = entity.getId();
//        this.name = entity.getName();
//        this.price = entity.getPrice();
//        this.details = entity.getDetails();
//        this.saleStatus = entity.isSaleStatus();
//        this.productStat = entity.getProductStat();
//        this.createdTime = entity.getCreatedDate();
//        this.category = entity.getCategory();
//        this.giver = GiverResponseForProduct.from(entity.getGiver());
//    }

    public static ProductListResponseDto from(Product product){
        return ProductListResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .details(product.getDetails())
                .saleStatus(product.isSaleStatus())
                .productStat(product.getProductStat())
                .createdTime(product.getCreatedDate())
                .category(product.getCategory())
                .giver(GiverResponseForProduct.from(product.getGiver()))
                .build();
    }

}
