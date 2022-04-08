package com.cabbage.grabit.api.giver;

import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.product.dto.response.ProductListResponseToGiverDto;
import com.cabbage.grabit.domain.product.support.ProductQuerySupport;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class GiverService {

    private final ProductRepository productRepository;
    private final GiverRepository giverRepository;
    private final ProductQuerySupport productQuerySupport;

    public Giver findById(Long id){
        return giverRepository.findById(id).orElseThrow(()->new ApiException(ApiStatus.GIVER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Page<ProductListResponseToGiverDto> searchByDate(Long giverId, LocalDate startDate){
        LocalDateTime startDateTime = startDate.atStartOfDay();
        Pageable paging = PageRequest.of(0, 5);
        return productRepository.findByDate(giverId, startDateTime, paging).map(ProductListResponseToGiverDto::from);
    }

}
