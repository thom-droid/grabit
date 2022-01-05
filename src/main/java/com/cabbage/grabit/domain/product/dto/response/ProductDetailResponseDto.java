package com.cabbage.grabit.domain.product.dto.response;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductStat;
import com.cabbage.grabit.domain.product_review.dto.response.ReviewResponseForProduct;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.dto.response.GiverResponseForProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailResponseDto {

    private Long itemId;
    private Category category;
    private String name;
    private Integer price;
    private String details;
    private String image;
    private boolean saleStatus;
    private ProductStat productStat;
    private GiverResponseForProduct giver;
    @Builder.Default
    private Set<Region> regions = new HashSet<>();

    @Builder.Default
    private List<ReviewResponseForProduct> reviewList = new ArrayList<>();

//    public ProductDetailResponseDto(Product product) {
//        this.itemId = product.getId();
//        this.category = product.getCategory();
//        this.name = product.getName();
//        this.price = product.getPrice();
//        this.details = product.getDetails();
//        this.image = product.getImage();
//        this.saleStatus = product.isSaleStatus();
//        this.productStat = product.getProductStat();
//        this.giver = new GiverResponseForProduct(product.getGiver());
//        this.regions = product.getRegions();
//        this.reviewList = product.getProductReviewList().stream()
//                .map(ReviewResponseForProduct::new).collect(Collectors.toList());
//    }

    public static ProductDetailResponseDto from(Product product){
        return ProductDetailResponseDto.builder()
                .itemId(product.getId())
                .category(product.getCategory())
                .name(product.getName())
                .price(product.getPrice())
                .details(product.getDetails())
                .image(product.getImage())
                .saleStatus(product.isSaleStatus())
                .productStat(product.getProductStat())
                .giver(GiverResponseForProduct.from(product.getGiver()))
                .regions(product.getRegions())
                .reviewList(product.getProductReviewList()
                        .stream().map(ReviewResponseForProduct::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
