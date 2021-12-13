package com.cabbage.grabit.api.subscription;

import com.cabbage.grabit.api.product.ProductFacade;
import com.cabbage.grabit.api.product.ProductService;
import com.cabbage.grabit.api.shipment.RegionFacade;
import com.cabbage.grabit.api.shipment.RegionService;
import com.cabbage.grabit.api.taker.TakerFacade;
import com.cabbage.grabit.api.taker.TakerService;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.shipment.dto.ShippingAddressPostRequestDto;
import com.cabbage.grabit.domain.subscription.dto.SubscriptionListResponseDto;
import com.cabbage.grabit.domain.subscription.dto.SubscriptionPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import com.cabbage.grabit.domain.user.TakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SubscriptionFacade {

    private final SubscriptionService subscriptionService;
    private final ProductService productService;
    private final TakerService takerService;
    private final RegionService regionService;

    public Page<SubscriptionListResponseDto> getSubscriptionByTaker(Long takerId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return subscriptionService.getSubscriptionByTakerId(takerId, pageable);
    }

    public Long postSubscription(SubscriptionPostRequestDto requestDto){

        Taker taker = takerService.getTakerById(requestDto.getTaker().getId());
        Product product = productService.getProductById(requestDto.getProduct().getId());
        Region region = regionService.getRegionById(requestDto.getShippingAddress().getRegion().getId());

        // prepare address dto to create a new shipping address entity
        ShippingAddressPostRequestDto addressDto = ShippingAddressPostRequestDto.toDto(requestDto.getShippingAddress());
//        ShippingAddress addressDto = requestDto.getShippingAddress();

        return subscriptionService.postSubscription(taker, product, region, requestDto, addressDto);

    }
}
