package com.cabbage.grabit.api.product_review;

import com.cabbage.grabit.api.ApiTestEnvironment;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.product_review.dto.ReviewPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductReviewServiceTest extends ApiTestEnvironment {

    @Test
    @Transactional
    public void postReview() {

        int rate = 4;
        String content = "아주 맛이 좋네요";
        Product product = productService.getProductById(12L);
        Taker taker = takerService.getTakerById(3L);

        ReviewPostRequestDto requestDto = ReviewPostRequestDto.builder()
                .content(content)
                .product(product)
                .taker(taker)
                .rate(rate)
                .build();

//        ProductReview productReview = ProductReview.create(taker, product, requestDto);

        Long result = productReviewService.postReview(taker, product, requestDto);

        assertThat(result).isEqualTo(1L);

    }
}