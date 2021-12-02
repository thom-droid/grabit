package com.cabbage.grabit.domain.product.dto;

import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.user.Giver;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class PostProductRequestDto {

    private Giver giver;
    private Category category;
    private String name;
    private Integer price;
    private String image;
    private String details;
    private Set<Region> regions = new HashSet<>();

    @Builder
    public PostProductRequestDto(Giver giver, Category category, String name, Integer price, String image, String details, Set<Region> regions) {
        this.giver = giver;
        this.category = category;
        this.name = name;
        this.price = price;
        this.image = image;
        this.details = details;
//        this.regions.addAll(regions);
    }

    public Product toEntity(){
        return Product.builder()
                .details(details)
                .category(category)
                .name(name)
                .price(price)
                .image(image)
                .build();
    }
}
