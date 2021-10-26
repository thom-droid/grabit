package com.cabbage.grabit.web;

import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.service.products.ProductsService;
import com.cabbage.grabit.web.dto.request.PostProductRequestDto;
import com.cabbage.grabit.web.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductsApiController {

    private final ProductsService productsService;

    @PostMapping("/api/v1/products")
    public Long save(@RequestBody PostProductRequestDto requestDto){
        return productsService.postProduct(requestDto);
    }

    @PutMapping("/api/v1/products/{id}")
    public Long switchStatus(@PathVariable Long id){
        return productsService.switchStatus(id);
    }

    @DeleteMapping("/api/v1/products/{id}")
    public Long delete(@PathVariable Long id){
        productsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/giver/products")
    public List<ProductResponseDto> findMyProducts(Giver giver){
        return productsService.selectProductListByGiver(giver);
    }

    @GetMapping("/api/v1/giver/products2/{giverId}")
    public List<ProductResponseDto> findMyProducts2(@PathVariable Long giverId){
        return productsService.selectProductListByGiver2(giverId);
    }



}
