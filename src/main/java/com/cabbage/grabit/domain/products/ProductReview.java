package com.cabbage.grabit.domain.products;

import com.cabbage.grabit.domain.user.Taker;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
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
    @JoinColumn(name="PRODUCTS_ID", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="TAKER_ID", nullable = false)
    private Taker taker;

    @OneToOne(mappedBy = "productReview")
    private Reply reply;

    @Builder
    public ProductReview(Product product, Taker taker, String content, BigDecimal rate) {
        this.product = product;
        this.taker = taker;
        this.content = content;
        this.rate = rate;
    }
}
