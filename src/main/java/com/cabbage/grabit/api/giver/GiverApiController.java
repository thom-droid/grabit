package com.cabbage.grabit.api.giver;

import com.cabbage.grabit.api.product.ProductFacade;
import com.cabbage.grabit.api.product.ProductService;
import com.cabbage.grabit.exception.ApiResult;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/giver")
public class GiverApiController {

    private ProductFacade productFacade;
    private ProductService productService;

    @PutMapping("/{giverId}/{productId}")
    //
    public ApiResult switchStatus(@PathVariable Long giverId,
                                  @PathVariable Long productId){

        return ApiResult.of(ApiStatus.SUCCESS, productFacade.switchProductStatus(giverId, productId));
    }
}
