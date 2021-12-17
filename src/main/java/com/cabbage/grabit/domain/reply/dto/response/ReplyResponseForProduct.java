package com.cabbage.grabit.domain.reply.dto.response;

import com.cabbage.grabit.domain.reply.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReplyResponseForProduct {

    private String content;
    private LocalDateTime createdTime;

    public ReplyResponseForProduct(Reply reply) {
        this.createdTime = reply.getCreatedDate();
        this.content = reply.getContent();
    }
}
