package com.cabbage.grabit.domain.product.dto.response;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductStat;
import com.cabbage.grabit.domain.product_review.dto.response.ReviewResponseForProduct;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.user.dto.response.GiverResponseForProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProductDetailResponseToGiver {

    private Long itemId;
    private Category category;
    private String name;
    private Integer price;
    private String details;
    private String image;
    private boolean saleStatus;
    private ProductStat productStat;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    @Builder.Default
    private Set<Region> regions = new HashSet<>();

    @Builder.Default
    private List<ReviewResponseForProduct> reviewList = new ArrayList<>();

    public static ProductDetailResponseToGiver from(Product product){
        return ProductDetailResponseToGiver.builder()
                .itemId(product.getId())
                .category(product.getCategory())
                .name(product.getName())
                .price(product.getPrice())
                .details(product.getDetails())
                .image(product.getImage())
                .saleStatus(product.isSaleStatus())
                .productStat(product.getProductStat())
                .createdTime(product.getCreatedDate())
                .modifiedTime(product.getModifiedDate())
                .build();
    }
}
