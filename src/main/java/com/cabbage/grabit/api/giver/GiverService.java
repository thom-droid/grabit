package com.cabbage.grabit.api.giver;

import com.cabbage.grabit.domain.product.dto.response.ProductListResponseToGiverDto;
import com.cabbage.grabit.domain.product.support.ProductQuerySupport;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class GiverService {

    private final GiverRepository giverRepository;
    private final ProductQuerySupport productQuerySupport;

    public Giver findById(Long id){
        return giverRepository.findById(id).orElseThrow(()->new ApiException(ApiStatus.GIVER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Page<ProductListResponseToGiverDto> searchByDate(Long giverId, LocalDate startDate, LocalDate endDate){

        return productQuerySupport.findProductsByDate(giverId, startDate,endDate);
    }

}
