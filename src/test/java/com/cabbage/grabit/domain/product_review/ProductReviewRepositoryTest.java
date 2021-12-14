package com.cabbage.grabit.domain.product_review;

import com.cabbage.grabit.domain.JpaTestEnvironment;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product_review.dto.ReviewPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductReviewRepositoryTest extends JpaTestEnvironment {

    @Test
    @Transactional
    public void save(){
        int rate = 4;
        String content = "아주 맛이 좋네요";

        Taker taker = takerRepository.findById(3L).orElseThrow(()-> new IllegalArgumentException("no"));
        Product product = productRepository.findById(2L).orElseThrow(()-> new IllegalArgumentException("no product found"));
        ProductReview productReview = ReviewPostRequestDto.builder()
                .rate(rate)
                .content(content)
                .taker(taker)
                .product(product).build().toEntity();

        taker.getProductReviewList().add(productReview);
        product.getProductReviewList().add(productReview);

        productReview = productReviewRepository.save(productReview);

        String savedContent = productReview.getContent();
        Long savedId = productReview.getId();
        System.out.println("id "+ savedId);
        assertThat(savedContent).isEqualTo(content);

    }
}
