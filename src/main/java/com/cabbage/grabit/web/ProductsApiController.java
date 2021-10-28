package com.cabbage.grabit.web;

import com.cabbage.grabit.domain.products.Products;
import com.cabbage.grabit.domain.products.ProductsRepository;
import com.cabbage.grabit.service.products.ProductsService;
import com.cabbage.grabit.web.dto.request.PostProductRequestDto;
import com.cabbage.grabit.web.dto.response.ProductListResponseDto;
import com.cabbage.grabit.web.dto.response.ProductResponseDto;
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
public class ProductsApiController {

    private final ProductsService productsService;
    private final ProductsRepository productsRepository;

    @PostMapping
    public Long save(@RequestBody PostProductRequestDto requestDto){
        return productsService.postProduct(requestDto);
    }

    @PutMapping("/{id}")
    public Long switchStatus(@PathVariable Long id){
        return productsService.switchStatus(id);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        productsService.delete(id);
        return id;
    }

    @GetMapping("/giver/{giverId}")
    public List<ProductResponseDto> findMyProducts(@PathVariable Long giverId){
        return productsService.selectProductListByGiver(giverId);
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> findAllProducts(
            @RequestParam(required = false) String sortByPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        try{
            List<ProductListResponseDto> productsList = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);

            Page<ProductListResponseDto> pageProductList;

            if (sortByPrice==null){
                pageProductList = productsRepository.findAll(paging).map(ProductListResponseDto::new);
            }
            else if (sortByPrice.equals("ASC")) {
                paging = PageRequest.of(page, size, Sort.by("price").ascending());
                pageProductList = productsRepository.findAll(paging).map(ProductListResponseDto::new);
            }
            else {
                paging = PageRequest.of(page, size, Sort.by("price").descending());
                pageProductList = productsRepository.findAll(paging).map(ProductListResponseDto::new);
            }

            productsList = pageProductList.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("products", productsList);
            response.put("currentPage", pageProductList.getNumber());
            response.put("totalItem", pageProductList.getTotalElements());
            response.put("totalPages", pageProductList.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
