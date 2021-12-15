package com.cabbage.grabit.api.reply;

import com.cabbage.grabit.api.giver.GiverService;
import com.cabbage.grabit.api.product_review.ProductReviewService;
import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.reply.dto.ReplyPostRequestDto;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ReplyFacade {

    private final ReplyService replyService;
    private final GiverService giverService;
    private final ProductReviewService productReviewService;

    public Long postReply(ReplyPostRequestDto requestDto){
        Giver giver = giverService.findById(requestDto.getGiver().getId());
        ProductReview productReview = productReviewService.findById(requestDto.getProductReview().getId());

        // when reply on this review already exists, invoke exception
        if(Objects.nonNull(productReview.getReply())){
            throw new ApiException(ApiStatus.ONE_REPLY_PER_REVIEW);
        }

        return replyService.postReply(giver, productReview, requestDto);
    }

}
