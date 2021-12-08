package com.cabbage.grabit.api.product;

import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.exception.ApiResult;
import com.cabbage.grabit.exception.ApiStatus;
import com.cabbage.grabit.domain.product.dto.ProductPostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductApiController {

    private final ProductService productService;
    private final ProductFacade productFacade;
    private final ProductRepository productRepository;

    @PostMapping
    public ApiResult save(@RequestBody ProductPostRequestDto requestDto){

        return ApiResult.of(ApiStatus.SUCCESS, productFacade.postProduct(requestDto));
    }

    @PutMapping("/{id}")
    public ApiResult switchStatus(@PathVariable Long id){
        return ApiResult.of(ApiStatus.SUCCESS, productService.switchStatus(id));
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        productService.delete(id);
        return id;
    }

    @GetMapping("/giver/{giverId}")
    public ApiResult findMyProducts(@PathVariable Long giverId,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size){
        return ApiResult.of(ApiStatus.SUCCESS, productService.selectProductListByGiver(giverId, page, size));
    }

    @GetMapping
    public ApiResult findAllProducts(
            @RequestParam(required = false) String sortByPrice,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){

        return ApiResult.of(ApiStatus.SUCCESS, productFacade.getAllProducts(sortByPrice, category, page, size));
    }

}
