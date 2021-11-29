package com.cabbage.grabit.domain.products;

import com.cabbage.grabit.domain.BaseTimeEntity;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.user.Giver;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String details;

    @Column(name = "sales_status", nullable = false)
    private boolean saleStatus;

    @Embedded
    private ProductStat productStat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GIVER_ID", referencedColumnName = "ID", nullable = false)
    private Giver giver;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "REGION_ID", nullable = false)
    @Builder.Default
    private final Set<Region> regions = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @Builder.Default
    private final List<ProductReview> productReviewList = new ArrayList<>();

    @Builder
    public Product(Giver giver, Category category, String image, String name, Integer price, String details, Set<Region> regions) {
        this.giver = giver;
        this.category = category;
        this.image = image;
        this.name = name;
        this.price = price;
        this.details = details;
        this.saleStatus = true;
        this.regions.addAll(regions);
        if(!giver.getProductList().contains(this))
            giver.getProductList().add(this);
    }

    public void switchStatus(){
        saleStatus = !saleStatus;
    }

//    public void addGiver(Giver giver){
//        if(!giver.getProducts().contains(this)){
//            giver.getProducts().add(this);
//        }
//        this.giver = giver;
//    }

}
