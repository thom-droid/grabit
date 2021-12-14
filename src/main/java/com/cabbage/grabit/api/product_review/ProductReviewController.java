package com.cabbage.grabit.api.product_review;

import com.cabbage.grabit.domain.product_review.dto.ReviewPostRequestDto;
import com.cabbage.grabit.exception.ApiResult;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ProductReviewController {

    private final ProductReviewFacade productReviewFacade;

    @PostMapping
    public ApiResult postProductReview(@RequestBody ReviewPostRequestDto requestDto){

        return ApiResult.of(ApiStatus.SUCCESS, productReviewFacade.postReview(requestDto));
    }

    @PutMapping("/{reviewId}")
    public ApiResult updateProductReview(@RequestBody String content,
                                         @PathVariable("reviewId") Long reviewId){
        return ApiResult.of(ApiStatus.SUCCESS, productReviewFacade.updateReview(content, reviewId));
    }
}
