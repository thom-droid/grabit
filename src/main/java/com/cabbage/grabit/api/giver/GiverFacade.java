package com.cabbage.grabit.api.giver;

import com.cabbage.grabit.domain.product.dto.response.ProductListResponseToGiverDto;
import com.cabbage.grabit.domain.user.Giver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Component
public class GiverFacade {

    private final GiverService giverService;

    public Page<ProductListResponseToGiverDto> searchByDate(LocalDate startDate, Long giverId){
        Giver giver = giverService.findById(giverId);
        return giverService.searchByDate(giver.getId(), startDate);
    }

}
