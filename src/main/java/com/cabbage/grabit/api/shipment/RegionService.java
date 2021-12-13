package com.cabbage.grabit.api.shipment;

import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.shipment.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public Region getRegionById(Long regionId){
        return regionRepository.findById(regionId).orElseThrow(() -> new IllegalArgumentException("no region found"));
    }
}
