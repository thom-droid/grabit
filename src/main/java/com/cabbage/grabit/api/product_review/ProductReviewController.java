package com.cabbage.grabit.api.product_review;

import com.cabbage.grabit.domain.product_review.dto.request.ReviewPostRequestDto;
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

    // TODO session 활용해서 해당 글을 쓴 사람만 수정 가능하도록 추가해야함
    @PutMapping("/{reviewId}")
    public ApiResult updateProductReview(@RequestBody String content,
                                         @PathVariable("reviewId") Long reviewId){
        return ApiResult.of(ApiStatus.SUCCESS, productReviewFacade.updateReview(content, reviewId));
    }
}
