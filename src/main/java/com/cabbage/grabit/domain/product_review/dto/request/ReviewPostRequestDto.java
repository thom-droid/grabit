package com.cabbage.grabit.domain.product_review.dto.request;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.user.Taker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewPostRequestDto {

    private String content;
    private int rate;
    private Product product;
    private Taker taker;

    public ProductReview toEntity(){
        return ProductReview.builder()
                .content(content)
                .rate(rate)
                .product(product)
                .taker(taker)
                .build();
    }
}
