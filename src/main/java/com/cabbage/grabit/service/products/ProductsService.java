package com.cabbage.grabit.service.products;

import com.cabbage.grabit.domain.products.Products;
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

    @Transactional
    public Long switchStatus(Long id){
        Products product = productsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 제품을 찾을 수 없습니다. 제품 번호 : "+ id));

        // product status to unavailable
        product.switchStatus();

        return id;
    }

    public void delete(Long id){
        Products product = productsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 제품을 찾을 수 없습니다. 제품 번호 : "+ id));

        productsRepository.deleteById(id);
    }

}
