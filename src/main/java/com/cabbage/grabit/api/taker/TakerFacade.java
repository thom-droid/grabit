package com.cabbage.grabit.api.taker;

import com.cabbage.grabit.domain.user.Taker;
import com.cabbage.grabit.domain.user.TakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TakerFacade {

    private final TakerRepository takerRepository;

    public Taker getTakerById(Long takerId){
        return takerRepository.findById(takerId).orElseThrow(()-> new IllegalArgumentException("no taker found"));
    }

}
