package com.cabbage.grabit.domain.user;

import com.cabbage.grabit.domain.product.ProductReview;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.subscription.Subscription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@DiscriminatorValue("TAKER")
@Entity
public class Taker extends Member {

    @Column(nullable = false)
    private String nickname;

    @JsonIgnore
    @OneToMany(mappedBy = "taker", fetch = FetchType.LAZY)
    private final List<ProductReview> productReviewList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "taker", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private final List<ShippingAddress> shippingAddressList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "taker", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private final List<Subscription> subscriptionList = new ArrayList<>();

    @Builder
    public Taker(String name, String email, String picture, Role role, String nickname) {
        super(name, email, picture, role);
        this.nickname = nickname;
    }
}
