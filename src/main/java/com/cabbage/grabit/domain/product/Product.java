package com.cabbage.grabit.domain.product;

import com.cabbage.grabit.domain.BaseTimeEntity;
import com.cabbage.grabit.domain.product.dto.ProductPostRequestDto;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.user.Giver;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PRODUCT")
@Entity
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

    @Setter
    @Column(name = "sale_status", nullable = false)
    private boolean saleStatus;

    @Embedded
    private ProductStat productStat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GIVER_ID", referencedColumnName = "ID", nullable = false)
    private Giver giver;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "PRODUCT_REGION",
        joinColumns = @JoinColumn(name = "PRODUCT_ID"),
        inverseJoinColumns = @JoinColumn(name = "REGION_ID"))
    @Builder.Default
    private final Set<Region> regions = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @Builder.Default
    private final List<ProductReview> productReviewList = new ArrayList<>();

    public static Product create(Giver giver, Set<Region> regionSet, ProductPostRequestDto requestDto){
        Product product = ProductPostRequestDto.builder()
                .giver(giver)
                .category(requestDto.getCategory())
                .details(requestDto.getDetails())
                .image(requestDto.getImage())
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .regions(regionSet)
                .build()
                .toEntity();
        product.setSaleStatus(true);
        giver.getProductList().add(product);

        return product;
    }

    public void switchStatus(){
        saleStatus = !saleStatus;
    }

}
