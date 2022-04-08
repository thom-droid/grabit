package com.cabbage.grabit.domain.product;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ProductStat {

    @Column(precision = 7)
    private double subscriptionCount;

    @Column(precision = 2, scale = 1)
    private double rate;

    @Column(precision = 7)
    private int reviewCount;

    @Column(nullable = true)
    private int five;
    @Column(nullable = true)
    private int four;
    @Column(nullable = true)
    private int three;
    @Column(nullable = true)
    private int two;
    @Column(nullable = true)
    private int one;

    public void updateRate(int newRate){

        switch (newRate){
            case 5:
                five++;
                break;
            case 4:
                four++;
                break;
            case 3:
                three++;
                break;
            case 2:
                two++;
                break;
            case 1:
                one++;
                break;
        }

        reviewCount++;

        rate = (double) (five*5+four*4+three*3+two*2+one) / reviewCount;
    }
}
