package com.cabbage.grabit.domain.subscription;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.user.Taker;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean isSubscribed;

    @Column(nullable = false)
    private boolean shippingStatus;

    @Column(nullable = false)
    private Integer price;

    private String shippingMessage;

    @Column(nullable = false)
    private String recipient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name= "TAKER_ID", nullable = false)
    private Taker taker;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SHIPPINGADDRESS_ID", nullable = false)
    private ShippingAddress shippingAddress;

}
