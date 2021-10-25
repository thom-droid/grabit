package com.cabbage.grabit.domain.products;

import com.cabbage.grabit.domain.user.Giver;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GIVER_ID", referencedColumnName = "ID")
    private Giver giver;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Categories categories;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private boolean saleStatus;

    @Column(precision = 7, scale = 0)
    private BigDecimal subscriptionCount;

    @Column(precision = 2, scale = 1)
    private BigDecimal rate;

    @Column(precision = 7)
    private BigDecimal reviewCount;

    @Builder
    public Products(Giver giver, Categories categories, String name, Integer price, String details) {
        this.giver = giver;
        this.categories = categories;
        this.name = name;
        this.price = price;
        this.details = details;
        this.saleStatus = true;
    }

    public void switchStatus(){
        saleStatus = !saleStatus;
    }

}
