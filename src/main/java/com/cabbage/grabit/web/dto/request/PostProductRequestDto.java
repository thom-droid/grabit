package com.cabbage.grabit.web.dto.request;

import com.cabbage.grabit.domain.products.Categories;
import com.cabbage.grabit.domain.products.Products;
import com.cabbage.grabit.domain.user.Giver;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostProductRequestDto {

    private Giver giver;
    private Categories categories;
    private String name;
    private Integer price;
    private String details;
    private boolean saleStatus;

    @Builder
    public PostProductRequestDto(Giver giver, Categories categories, String name, Integer price, String details) {
        this.giver = giver;
        this.categories = categories;
        this.name = name;
        this.price = price;
        this.details = details;
        this.saleStatus = true;
    }

    public Products toEntity(){
        return Products.builder().giver(giver).details(details).categories(categories).name(name).price(price).build();
    }
}
