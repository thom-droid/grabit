package com.cabbage.grabit.domain.product_review;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.Reply;
import com.cabbage.grabit.domain.product_review.dto.ReviewPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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

        taker.getProductReviewList().add(productReview);
        product.getProductReviewList().add(productReview);

        return productReview;
    }
}
