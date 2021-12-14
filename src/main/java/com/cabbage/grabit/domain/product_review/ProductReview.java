package com.cabbage.grabit.domain.product_review;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.Reply;
import com.cabbage.grabit.domain.product_review.dto.ReviewPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
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
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, precision = 1)
    private BigDecimal rate;

    @Column
    private Boolean isModified;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="TAKER_ID", nullable = false)
    private Taker taker;

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

        Objects.requireNonNull(productReview, "this is null");
        taker.getProductReviewList().add(productReview);
        product.getProductReviewList().add(productReview);

        return productReview;
    }

    public void update(String content){
        this.content = content;
        this.isModified = true;
    }
}
