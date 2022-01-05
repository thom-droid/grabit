package com.cabbage.grabit.domain.reply.dto.response;

import com.cabbage.grabit.domain.reply.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyResponseForProduct {

    private String content;
    private LocalDateTime createdTime;

    public ReplyResponseForProduct(Reply reply) {
        this.createdTime = reply.getCreatedDate();
        this.content = reply.getContent();
    }

    public static ReplyResponseForProduct from(Reply reply){
        return ReplyResponseForProduct.builder()
                .content(reply.getContent())
                .createdTime(reply.getCreatedDate())
                .build();
    }
}
