package com.cabbage.grabit.api.product_review;

import com.cabbage.grabit.api.product.ProductService;
import com.cabbage.grabit.api.taker.TakerService;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product_review.dto.request.ReviewPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
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

        // invoke exception when product review already exists
        if(product.hasAlreadyReview())
            throw new ApiException(ApiStatus.DUPLICATED_REVIEW);

        // if the product is not subscribed
        if(!taker.hasSubscribed(product)){
            throw new ApiException(ApiStatus.REVIEW_NOT_SUBSCRIBED);
        }

        return productReviewService.postReview(taker, product, requestDto);

    }

    public Long updateReview(String content, Long reviewId){

        return productReviewService.updateReview(content, reviewId);
    }

}
