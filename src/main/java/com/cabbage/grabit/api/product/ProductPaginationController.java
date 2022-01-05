package com.cabbage.grabit.api.product;

import com.cabbage.grabit.util.SearchParam;
import com.cabbage.grabit.exception.ApiResult;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** test controller*/

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductPaginationController {

    private final ProductFacade productFacade;
    private final ProductService productService;

    @GetMapping
    public ApiResult getProductListPagedWithParam(
            @RequestParam(required = false, defaultValue = "name") String sort,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword
    ){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).ascending());

        SearchParam searchParam = SearchParam.of(pageable, keyword, category, sort);

        return ApiResult.of(ApiStatus.SUCCESS, productService.getProductsPaginatedWithParam(searchParam));
    }
}
