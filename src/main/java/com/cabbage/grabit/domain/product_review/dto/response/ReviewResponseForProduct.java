package com.cabbage.grabit.domain.product_review.dto.response;

import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.reply.dto.response.ReplyResponseForProduct;
import com.cabbage.grabit.domain.user.dto.response.TakerResponseForProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewResponseForProduct {

    private String content;
    private int rate;
    private Boolean isModified;
    private TakerResponseForProduct taker;
    private ReplyResponseForProduct reply;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public ReviewResponseForProduct(ProductReview productReview) {
        this.content = productReview.getContent();
        this.rate = productReview.getRate();
        this.isModified = productReview.getIsModified();
        this.createdTime = productReview.getCreatedDate();
        this.modifiedTime = productReview.getModifiedDate();
        this.taker = new TakerResponseForProduct(productReview.getTaker());
        this.reply = new ReplyResponseForProduct(productReview.getReply());
    }
}
