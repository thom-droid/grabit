package com.cabbage.grabit.domain.reply;

import com.cabbage.grabit.domain.BaseTimeEntity;
import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.reply.dto.ReplyPostRequestDto;
import com.cabbage.grabit.domain.user.Giver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "GIVER_ID")
    private Giver giver;

    @MapsId
    @OneToOne
    @JoinColumn(name = "REVIEW_ID")
    private ProductReview productReview;

    public static Reply create(Giver giver, ProductReview productReview, ReplyPostRequestDto requestDto){
        Reply reply = ReplyPostRequestDto.builder()
                .content(requestDto.getContent())
                .giver(giver)
                .productReview(productReview)
                .build()
                .toEntity();
        giver.getReplyList().add(reply);
        productReview.setReply(reply);

        return reply;
    }
}
