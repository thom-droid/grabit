package com.cabbage.grabit.domain.product;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ProductStat {

    @Column(precision = 7, scale = 0)
    private BigDecimal subscriptionCount;

    @Column(precision = 2, scale = 1)
    private BigDecimal rate;

    @Column(precision = 7)
    private BigDecimal reviewCount;

}
