package com.cabbage.grabit.api.giver;

import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GiverService {

    private final GiverRepository giverRepository;

    public Giver findById(Long id){
        return giverRepository.findById(id).orElseThrow(()->new ApiException(ApiStatus.GIVER_NOT_FOUND));
    }

}
