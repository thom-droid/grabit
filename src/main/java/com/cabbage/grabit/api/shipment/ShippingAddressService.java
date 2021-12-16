package com.cabbage.grabit.api.shipment;

import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.shipment.ShippingAddressRepository;
import com.cabbage.grabit.domain.shipment.dto.ShippingAddressListToTakerResponseDto;
import com.cabbage.grabit.domain.shipment.dto.ShippingAddressPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShippingAddressService {

    private ShippingAddressRepository shippingAddressRepository;

    public Page<ShippingAddressListToTakerResponseDto> getShippingAddressListByTaker(Long takerId, Pageable paging){
        return shippingAddressRepository.findByTakerId(takerId, paging).map(ShippingAddressListToTakerResponseDto::new);
    }

    public Long postShippingAddress(Taker taker, Region region, ShippingAddressPostRequestDto requestDto) {

        // set all the other address to false when a new address is set true
        if(requestDto.getIsDefault()){
            List<ShippingAddress> addressList = shippingAddressRepository.findAllByTakerId(taker.getId());
            addressList.forEach((a) -> a.setDefault(false));
        }

        return shippingAddressRepository.save(ShippingAddress.create(taker, region, requestDto)).getId();
    }
}
