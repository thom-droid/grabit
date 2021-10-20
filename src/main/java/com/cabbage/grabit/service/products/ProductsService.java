package com.cabbage.grabit.service.products;

import com.cabbage.grabit.domain.products.ProductsRepository;
import com.cabbage.grabit.web.dto.request.PostProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Transactional
    public Long postProduct(PostProductRequestDto requestDto){
        return productsRepository.save(requestDto.toEntity()).getId();
    }

}
