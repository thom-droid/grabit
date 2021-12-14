package com.cabbage.grabit.api.product_review;

import com.cabbage.grabit.api.product.ProductService;
import com.cabbage.grabit.api.taker.TakerService;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductStat;
import com.cabbage.grabit.domain.product_review.dto.ReviewPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductReviewFacade {

    private final ProductService productService;
    private final TakerService takerService;
    private final ProductReviewService productReviewService;

    public Long postReview(ReviewPostRequestDto requestDto){

        Taker taker = takerService.getTakerById(requestDto.getTaker().getId());
        Product product = productService.getProductById(requestDto.getProduct().getId());

        return productReviewService.postReview(taker, product, requestDto);

    }

    public Long updateReview(String content, Long reviewId){

        return productReviewService.updateReview(content, reviewId);
    }

}
