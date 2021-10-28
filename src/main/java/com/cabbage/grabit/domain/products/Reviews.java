package com.cabbage.grabit.domain.products;

import com.cabbage.grabit.domain.user.Taker;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
//@Entity
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="PRODUCTS_ID", nullable = false)
    private Products products;

    @ManyToOne
    @JoinColumn(name ="TAKER_ID", nullable = false)
    private Taker taker;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, precision = 1)
    private BigDecimal rate;

    @Builder
    public Reviews(Products products, Taker taker, String content, BigDecimal rate) {
        this.products = products;
        this.taker = taker;
        this.content = content;
        this.rate = rate;
    }
}
