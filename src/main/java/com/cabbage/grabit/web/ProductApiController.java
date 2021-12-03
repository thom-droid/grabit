package com.cabbage.grabit.web;

import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.exception.ApiResult;
import com.cabbage.grabit.exception.ApiStatus;
import com.cabbage.grabit.service.products.ProductFacade;
import com.cabbage.grabit.service.products.ProductService;
import com.cabbage.grabit.domain.product.dto.PostProductRequestDto;
import com.cabbage.grabit.domain.product.dto.ProductListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductApiController {

    private final ProductService productService;
    private final ProductFacade productFacade;
    private final ProductRepository productRepository;

    @PostMapping
    public ApiResult save(@RequestBody PostProductRequestDto requestDto){

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
    public ApiResult findMyProducts(@PathVariable Long giverId){
        return ApiResult.of(ApiStatus.SUCCESS, productService.selectProductListByGiver(giverId));
    }

    @GetMapping
    public ApiResult findAllProducts(
            @RequestParam(required = false) String sortByPrice,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){

        return ApiResult.of(ApiStatus.SUCCESS, productFacade.getAllProducts(sortByPrice, category, page, size));
//            List<ProductListResponseDto> productsList = new ArrayList<>();

//            Page<ProductListResponseDto> pageProductList;



//            if(category==null){
//                pageProductList = productRepository.findAll(paging).map(ProductListResponseDto::new);
//            } else {
//                pageProductList = productRepository.findByCategoriesContaining(category, paging).map(ProductListResponseDto::new);
//            }
//
//            productsList = pageProductList.getContent();
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("products", productsList);
//            response.put("currentPage", pageProductList.getNumber());
//            response.put("totalItem", pageProductList.getTotalElements());
//            response.put("totalPages", pageProductList.getTotalPages());

//            return new ResponseEntity<>(response, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

}
