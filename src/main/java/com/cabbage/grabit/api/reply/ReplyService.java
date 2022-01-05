package com.cabbage.grabit.api.reply;

import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.reply.Reply;
import com.cabbage.grabit.domain.reply.ReplyRepository;
import com.cabbage.grabit.domain.reply.dto.request.ReplyPostRequestDto;
import com.cabbage.grabit.domain.user.Giver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public Long postReply(Giver giver, ProductReview productReview, ReplyPostRequestDto requestDto){

        Reply reply = replyRepository.save(Reply.create(giver, productReview, requestDto));

        return reply.getId();
    }
}
