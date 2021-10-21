package com.cabbage.grabit.web;

import com.cabbage.grabit.service.products.ProductsService;
import com.cabbage.grabit.web.dto.request.PostProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
