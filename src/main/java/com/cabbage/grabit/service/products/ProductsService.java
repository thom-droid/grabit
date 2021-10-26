package com.cabbage.grabit.service.products;

import com.cabbage.grabit.domain.products.Products;
import com.cabbage.grabit.domain.products.ProductsRepository;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.web.dto.request.PostProductRequestDto;
import com.cabbage.grabit.web.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
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
    public List<ProductResponseDto> selectProductListByGiver(Giver giver){

        Giver giverEntity = giverRepository.findById(giver.getId()).orElseThrow(()-> new IllegalArgumentException("error"));
        List<Products> productsList = giverEntity.getProducts();
        List<ProductResponseDto> responseDtoList = new ArrayList<>();

        for(Products products:productsList){
            responseDtoList.add(new ProductResponseDto(products));
        }
        return responseDtoList;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> selectProductListByGiver2(Long giverId){
        Giver giverEntity = giverRepository.findById(giverId).orElseThrow(()-> new IllegalArgumentException("error"));
        return giverEntity.getProducts().stream().map(ProductResponseDto::new).collect(Collectors.toList());

    }





}
