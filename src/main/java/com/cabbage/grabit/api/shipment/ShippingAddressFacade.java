package com.cabbage.grabit.api.shipment;

import com.cabbage.grabit.api.taker.TakerFacade;
import com.cabbage.grabit.api.taker.TakerService;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.shipment.ShippingAddressRepository;
import com.cabbage.grabit.domain.shipment.dto.ShippingAddressListToTakerResponseDto;
import com.cabbage.grabit.domain.shipment.dto.ShippingAddressPostRequestDto;
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
public class ShippingAddressFacade {

    private final TakerService takerService;
    private final RegionService regionService;
    private final ShippingAddressService shippingAddressService;

    public Page<ShippingAddressListToTakerResponseDto> getTakerAddress(Long takerId, int page, int size){
        Taker taker = takerService.getTakerById(takerId);
        Pageable pageable = PageRequest.of(page,size);
        return shippingAddressService.getShippingAddressListByTaker(taker.getId(), pageable);
    }

    public Long postShippingAddress(ShippingAddressPostRequestDto requestDto){
        Taker taker = takerService.getTakerById(requestDto.getTaker().getId());
        Region region = regionService.getRegionById(requestDto.getRegion().getId());

        return shippingAddressService.postShippingAddress(taker, region, requestDto);
    }
}
