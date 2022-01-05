package com.cabbage.grabit.api.taker;

import com.cabbage.grabit.domain.user.Taker;
import com.cabbage.grabit.domain.user.TakerRepository;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TakerService {

    private final TakerRepository takerRepository;

    public Taker getTakerById(Long takerId){
        return takerRepository.findById(takerId).orElseThrow(()-> new ApiException(ApiStatus.TAKER_NOT_FOUND));
    }
}
