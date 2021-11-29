package com.cabbage.grabit.domain.user;

import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.subscription.Subscription;
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

    @OneToMany(mappedBy = "taker", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Builder.Default
    private final List<ShippingAddress> shippingAddressList = new ArrayList<>();

    @OneToMany(mappedBy = "taker", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Builder.Default
    private List<Subscription> subscriptionList;

    @Builder
    public Taker(String name, String email, String picture, Role role, String nickname) {
        super(name, email, picture, Role.ROLE_TAKER);
        this.nickname = nickname;
    }


}
