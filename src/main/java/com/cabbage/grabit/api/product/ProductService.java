package com.cabbage.grabit.api.product;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.product.dto.ProductListResponseDto;
import com.cabbage.grabit.domain.product.dto.ProductListResponseToGiverDto;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.domain.product.dto.ProductPostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final GiverRepository giverRepository;

    @Transactional
        public Long postProduct(Giver giver, Set<Region> regionSet, ProductPostRequestDto requestDto){
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
    public Page<ProductListResponseToGiverDto> selectProductListByGiver(Long giverId, int page, int size){
        Pageable paging = PageRequest.of(page, size);
        return productRepository.findByGiverId(giverId, paging).map(ProductListResponseToGiverDto::new);

    }

    @Transactional(readOnly = true)
    public Page<ProductListResponseDto> getAllProducts(String category, Pageable paging){

        if(category == null){
            return productRepository.findAll(paging).map(ProductListResponseDto::new);
        }

        return productRepository.findBySaleStatusAndCategoryContaining(category, paging).map(ProductListResponseDto::new);
    }


}
