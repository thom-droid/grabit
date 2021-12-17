package com.cabbage.grabit.domain.shipment;

import com.cabbage.grabit.domain.BaseTimeEntity;
import com.cabbage.grabit.domain.shipment.dto.ShippingAddressPostRequestDto;
import com.cabbage.grabit.domain.subscription.dto.SubscriptionPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="SHIPPING_ADDRESS")
public class ShippingAddress extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String addressDetail;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String recipient;

    @Setter
    private boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="REGION_ID")
    private Region region;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TAKER_ID")
    private Taker taker;

    public static ShippingAddress create(Taker taker, Region region, ShippingAddressPostRequestDto requestDto){
        ShippingAddress shippingAddress = ShippingAddressPostRequestDto.builder()
                .addressDetail(requestDto.getAddressDetail())
                .nickname(requestDto.getNickname())
                .recipient(requestDto.getRecipient())
                .isDefault(requestDto.getIsDefault())
                .region(region)
                .taker(taker)
                .build()
                .toEntity();
        taker.getShippingAddressList().add(shippingAddress);

        return shippingAddress;
    }


}
