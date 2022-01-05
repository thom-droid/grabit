package com.cabbage.grabit.domain.product.dto.request;

import com.cabbage.grabit.domain.product.ProductStat;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.user.Giver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPostRequestDto {

    private Giver giver;
    private Category category;
    private String name;
    private Integer price;
    private String image;
    private String details;
    private ProductStat productStat;
    @Builder.Default
    private Set<Region> regions = new HashSet<>();

    public Product toEntity(){
        return Product.builder()
                .giver(giver)
                .details(details)
                .category(category)
                .name(name)
                .price(price)
                .image(image)
                .regions(regions)
                .productStat(new ProductStat())
                .build();
    }
}
