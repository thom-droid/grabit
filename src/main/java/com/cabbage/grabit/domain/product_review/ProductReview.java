package com.cabbage.grabit.domain.product_review;

import com.cabbage.grabit.domain.BaseTimeEntity;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.reply.Reply;
import com.cabbage.grabit.domain.product_review.dto.ReviewPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductReview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, precision = 1)
    private int rate;

    @Column
    private Boolean isModified;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID", nullable = false)
    private Product product;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="TAKER_ID", nullable = false)
    private Taker taker;

    @Setter
    @OneToOne(mappedBy = "productReview")
    private Reply reply;

    public static ProductReview create(Taker taker,
                                       Product product,
                                       ReviewPostRequestDto requestDto){

        ProductReview productReview = ReviewPostRequestDto.builder()
                .taker(taker)
                .product(product)
                .content(requestDto.getContent())
                .rate(requestDto.getRate())
                .build()
                .toEntity();

            product.getProductReviewList().add(productReview);
            taker.getProductReviewList().add(productReview);

        return productReview;
    }

    public void update(String content){
        this.content = content;
        this.isModified = true;
    }

}
