package com.cabbage.grabit.service.products;

import com.cabbage.grabit.domain.products.Products;
import com.cabbage.grabit.domain.products.ProductsRepository;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.web.dto.request.PostProductRequestDto;
import com.cabbage.grabit.web.dto.response.ProductListResponseDto;
import com.cabbage.grabit.web.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final GiverRepository giverRepository;

    @Transactional
    public Long postProduct(PostProductRequestDto requestDto){

        Products product = requestDto.toEntity();
        requestDto.getGiver().addProduct(product);

        return productsRepository.save(product).getId();
    }

    @Transactional
    public Long switchStatus(Long id){
        Products product = productsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 제품을 찾을 수 없습니다. 제품 번호 : "+ id));

        // product status to unavailable
        product.switchStatus();

        return id;
    }

    @Transactional
    public void delete(Long id){
        Products product = productsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 제품을 찾을 수 없습니다. 제품 번호 : "+ id));

        productsRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> selectProductListByGiver(Long giverId){
        Giver giverEntity = giverRepository.findById(giverId).orElseThrow(()-> new IllegalArgumentException("error"));
        return giverEntity.getProducts().stream().map(ProductResponseDto::new).collect(Collectors.toList());

    }




    @Transactional(readOnly = true)
    public List<ProductListResponseDto> selectProductWithPage(){
        Pageable firstPageWithTwoElements = PageRequest.of(0,2);
        return productsRepository.findAll(firstPageWithTwoElements).stream().map(ProductListResponseDto::new).collect(Collectors.toList());
    }


}
