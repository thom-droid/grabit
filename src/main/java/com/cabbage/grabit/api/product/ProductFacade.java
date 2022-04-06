package com.cabbage.grabit.api.product;

import com.cabbage.grabit.api.giver.GiverService;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.product.dto.request.ProductPostRequestDto;
import com.cabbage.grabit.domain.product.dto.response.ProductDetailResponseToGiver;
import com.cabbage.grabit.domain.product.dto.response.ProductListResponseDto;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.shipment.RegionRepository;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProductFacade {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final GiverRepository giverRepository;
    private final RegionRepository regionRepository;
    private final GiverService giverService;

    public Long postProduct(ProductPostRequestDto requestDto){

        Giver giver = giverService.findById(requestDto.getGiver().getId());
        System.out.println("here i am :  " + giver.getId());
        /* TODO Set 으로 넘어온 파라미터 db에서 한 번에 조회해서 매핑하는 방법? */
        Set<Region> regionSet = new HashSet<>();
        requestDto.getRegions().forEach(r -> {
            regionSet.add(regionRepository.findById(r.getId()).orElseThrow(() -> new IllegalArgumentException("no region found")));
        });

        return productService.postProduct(giver, regionSet, requestDto);
    }

    public Page<ProductListResponseDto> getAllProducts(String sortByPrice, String category, int page, int size){

        Pageable paging;

        if(sortByPrice == null){
            paging = PageRequest.of(page, size);
        } else if (sortByPrice.equals("ASC")) {
            paging = PageRequest.of(page, size, Sort.by("price").ascending());
        } else {
            paging = PageRequest.of(page, size, Sort.by("price").descending());
        }

        return productService.getAllProducts(category, paging);

    }

    public ProductDetailResponseToGiver getProductDetailToGiver(Long productId, Long giverId){
        Giver entity = giverService.findById(giverId);
        Product product = productService.getProductById(productId);
        boolean isContained = entity.getProductList().contains(product);

        if(!isContained){
            throw new ApiException(ApiStatus.PRODUCT_NOT_BELONG_TO_GIVER);
        }

        return productService.getProductDetailToGiver(product);

    }

    public Long switchProductStatus(Long giverId, Long productId){
        Giver giver = giverService.findById(giverId);

        if(giver.getProductList().stream().anyMatch(product -> product.getId() == productId)){
            return productService.switchStatus(productId);
        }

        throw new ApiException(ApiStatus.PRODUCT_NOT_BELONG_TO_GIVER);
    }



}
