package com.cabbage.grabit.api.product;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.product.dto.response.ProductDetailResponseDto;
import com.cabbage.grabit.domain.product.dto.response.ProductDetailResponseToGiver;
import com.cabbage.grabit.domain.product.dto.response.ProductListResponseDto;
import com.cabbage.grabit.domain.product.dto.response.ProductListResponseToGiverDto;
import com.cabbage.grabit.domain.product.support.ProductQuerySupport;
import com.cabbage.grabit.domain.product.support.SearchParam;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.domain.product.dto.request.ProductPostRequestDto;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
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
    private final ProductQuerySupport productQuerySupport;

    // TODO 메서드 쿼리처럼 메서드 시그니처만으로 엔티티를 얻어오는 코드는 없을까?
    public Product getProductById(Long productId){
        return productRepository.findById(productId).orElseThrow(()->new ApiException(ApiStatus.PRODUCT_NOT_FOUND));
    }

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
        return productRepository.findByGiverId(giverId, paging).map(ProductListResponseToGiverDto::from);

    }

    @Transactional(readOnly = true)
    public Page<ProductListResponseDto> getAllProducts(String category, Pageable paging){

        if(category == null){
            return productRepository.findAll(paging).map(ProductListResponseDto::from);
        }

        return productRepository.findBySaleStatusAndCategoryContaining(category, paging).map(ProductListResponseDto::from);
    }


    public ProductDetailResponseDto getProductDetail(Long productId) {

        Product product = getProductById(productId);

        return ProductDetailResponseDto.from(product);
    }

    @Transactional(readOnly = true)
    public ProductDetailResponseToGiver getProductDetailToGiver(Product product){

        return ProductDetailResponseToGiver.from(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductListResponseDto> getProductsPaginatedWithParam(SearchParam searchParam) {

        return productQuerySupport.findProductsPaginatedWithParam(searchParam);
    }
}
