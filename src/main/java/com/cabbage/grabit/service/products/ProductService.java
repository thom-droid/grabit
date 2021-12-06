package com.cabbage.grabit.service.products;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.product.dto.ProductListResponseDto;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.domain.product.dto.PostProductRequestDto;
import com.cabbage.grabit.domain.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final GiverRepository giverRepository;

    @Transactional
        public Long postProduct(Giver giver, Set<Region> regionSet, PostProductRequestDto requestDto){
        Product product = Product.create(giver, regionSet, requestDto);

        return productRepository.save(product).getId();
    }

    @Transactional
    public Long switchStatus(Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 제품을 찾을 수 없습니다. 제품 번호 : "+ id));

        // product status to unavailable
        product.switchStatus();

        return id;
    }

    @Transactional
    public void delete(Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 제품을 찾을 수 없습니다. 제품 번호 : "+ id));

        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<ProductListResponseDto> selectProductListByGiver(Long giverId, Pageable pageable){

        return productRepository.findByGiverId(giverId, pageable).map(ProductListResponseDto::new);

//        Giver giverEntity = giverRepository.findById(giverId).orElseThrow(()-> new IllegalArgumentException("error"));
//        return null;
//                giverEntity.getProducts().stream().map(ProductResponseDto::new).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public Page<ProductListResponseDto> getAllProducts(String category, Pageable paging){

        if(category == null){
            return productRepository.findAll(paging).map(ProductListResponseDto::new);
        }

        return productRepository.findBySaleStatusAndCategoryContaining(category, paging).map(ProductListResponseDto::new);
    }


}
