package com.cabbage.grabit.api.giver;

import com.cabbage.grabit.api.product.ProductFacade;
import com.cabbage.grabit.domain.product.dto.request.ProductPostRequestDto;
import com.cabbage.grabit.exception.ApiResult;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/giver")
public class GiverApiController {

    // FIXME giver 정보 JWT에서 추출해서 사용자 일치 확인해야 함. Util 클래스 만들어서 공통 메서드로 처리하면 될 것 같음

    private final ProductFacade productFacade;

    @PutMapping("/{giverId}/{productId}")
    public ApiResult switchStatus(@PathVariable Long giverId,
                                  @PathVariable Long productId){

        return ApiResult.of(ApiStatus.SUCCESS, productFacade.switchProductStatus(giverId, productId));
    }

    @GetMapping("/product/detail")
    public ApiResult getMyProductDetail(@RequestParam Long productId,
                                        @RequestParam Long giverId){

        // FIXME 사용자 확인 코드로 바꿔야 함
//        if(giver==null){
//            throw new ApiException(ApiStatus.GIVER_NOT_FOUND);
//        }

        return ApiResult.of(ApiStatus.SUCCESS, productFacade.getProductDetailToGiver(productId, giverId));
    }

    // TODO product 등록 시 이미지 파일 multipart 처리해야함
    // TODO 인증 처리 해주어야 함
    @PostMapping("/save")
    public ApiResult save(@RequestBody ProductPostRequestDto requestDto){
        return ApiResult.of(ApiStatus.SUCCESS, productFacade.postProduct(requestDto));
    }

    @GetMapping("/list")
    public void searchByDate(@RequestParam String startDate,
                             @RequestParam String endDate){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

    }
}
