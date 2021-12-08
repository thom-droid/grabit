package com.cabbage.grabit.domain.shipment;

import com.cabbage.grabit.domain.user.Taker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="SHIPPING_ADDRESS")
public class ShippingAddress {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String addressDetail;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String recipient;

    private boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="REGION_ID")
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAKER_ID")
    private Taker taker;

}
