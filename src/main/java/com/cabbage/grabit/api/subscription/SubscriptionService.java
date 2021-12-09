package com.cabbage.grabit.api.subscription;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.shipment.ShippingAddressRepository;
import com.cabbage.grabit.domain.shipment.dto.ShippingAddressPostRequestDto;
import com.cabbage.grabit.domain.subscription.Subscription;
import com.cabbage.grabit.domain.subscription.SubscriptionRepository;
import com.cabbage.grabit.domain.subscription.dto.SubscriptionListResponseDto;
import com.cabbage.grabit.domain.subscription.dto.SubscriptionPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ShippingAddressRepository shippingAddressRepository;

    @Transactional(readOnly = true)
    public Page<SubscriptionListResponseDto> getSubscriptionByTakerId(Long takerId, Pageable paging){

        return subscriptionRepository.findAllByTakerId(takerId, paging).map(SubscriptionListResponseDto::new);

    }

    @Transactional
    public Long postSubscription(Taker taker, Product product, Region region,
                                 SubscriptionPostRequestDto requestDto,
                                 ShippingAddressPostRequestDto addressDto)
    {

        // set all the other address to false when a new address is set true
        if(addressDto.getIsDefault()){
            List<ShippingAddress> addressList = shippingAddressRepository.findAllByTakerId(taker.getId());
            addressList.forEach((a) -> a.setDefault(false));
        }

        // persist shipping address
        ShippingAddress shippingAddress = shippingAddressRepository.save(ShippingAddress.create(taker, region, addressDto));

        Subscription subscription = Subscription.create(taker, product, shippingAddress, requestDto);

        return subscriptionRepository.save(subscription).getId();
    }

}
