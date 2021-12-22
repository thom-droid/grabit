package com.cabbage.grabit.api.product;

import com.cabbage.grabit.config.aop.LogExecutionTime;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiResult;
import com.cabbage.grabit.exception.ApiStatus;
import com.cabbage.grabit.domain.product.dto.request.ProductPostRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductApiController {

    private final ProductService productService;
    private final ProductFacade productFacade;
    private final ProductRepository productRepository;

    // TODO product 등록 시 이미지 파일 multipart 처리해야함
    // TODO 인증 처리 해주어야 함
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

    @GetMapping("/giver/list/{giverId}")
    public ApiResult findMyProducts(@PathVariable Long giverId,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) throws ApiException{

        return ApiResult.of(ApiStatus.SUCCESS, productService.selectProductListByGiver(giverId, page, size));
    }

    @GetMapping("/giver/{productId}")
    public ApiResult getMyProductDetail(@PathVariable Long productId,
                                        @RequestParam Long giverId){

        // FIXME 인증 코드로 바꿔야 함
//        if(giver==null){
//            throw new ApiException(ApiStatus.GIVER_NOT_FOUND);
//        }

        return ApiResult.of(ApiStatus.SUCCESS, productFacade.getProductDetailToGiver(productId, giverId));

    }

    @LogExecutionTime
//    @Around("logExecutionTime(ProceedingJoinPoint joinPoint)")
    @GetMapping
    public ApiResult findAllProducts(
            @RequestParam(required = false) String sortByPrice,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){

        return ApiResult.of(ApiStatus.SUCCESS, productFacade.getAllProducts(sortByPrice, category, page, size));
    }

    @LogExecutionTime
    @GetMapping("/{productId}")
    public ApiResult getDetailedProduct(@PathVariable Long productId){

        return ApiResult.of(ApiStatus.SUCCESS, productService.getProductDetail(productId));
    }


}
