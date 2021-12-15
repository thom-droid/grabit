package com.cabbage.grabit.domain.reply.dto;

import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.reply.Reply;
import com.cabbage.grabit.domain.user.Giver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyPostRequestDto {

    private String content;
    private Giver giver;
    private ProductReview productReview;

    public Reply toEntity(){
        return Reply.builder()
                .content(content)
                .giver(giver)
                .productReview(productReview)
                .build();
    }

}
