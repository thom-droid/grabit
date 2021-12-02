package com.cabbage.grabit.domain.user;

import com.cabbage.grabit.domain.product.ProductReview;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.subscription.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DiscriminatorValue("TAKER")
@Entity
public class Taker extends Member {

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "taker", fetch = FetchType.LAZY)
    @Builder.Default
    private final List<ProductReview> productReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "taker", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Builder.Default
    private final List<ShippingAddress> shippingAddressList = new ArrayList<>();

    @OneToMany(mappedBy = "taker", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Builder.Default
    private final List<Subscription> subscriptionList = new ArrayList<>();


//    public static Taker createEntity (String name, String email, String picture, Role role, String nickname) {
//        Taker taker = Taker.builder().nickname(nickname).build();
//        taker.name = name;
//
//        return taker;
//
//    }


}
