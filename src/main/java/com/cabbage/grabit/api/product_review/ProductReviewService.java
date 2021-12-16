package com.cabbage.grabit.api.product_review;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.product_review.ProductReviewRepository;
import com.cabbage.grabit.domain.product_review.dto.ReviewPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;

    public ProductReview findById(Long id){
        return productReviewRepository.findById(id).orElseThrow(()-> new ApiException(ApiStatus.REVIEW_NOT_FOUND));
    }

    @Transactional
    public Long postReview(Taker taker,
                           Product product,
                           ReviewPostRequestDto requestDto){


        // check if this review is already referenced
        ProductReview productReview = ProductReview.create(taker, product, requestDto);

//        if(productReview.isReferenced(taker, product))
//            throw new ApiException(ApiStatus.DUPLICATED_REVIEW);

        // persist
//        productReview.setReference(taker, product);
        productReview = productReviewRepository.save(productReview);

        // update product stat (review count, review rate)
        int rate = requestDto.getRate();
        product.getProductStat().updateRate(rate);
        return productReview.getId();
    }

    @Transactional
    public Long updateReview(String content, Long reviewId){

        ProductReview productReview = findById(reviewId);
        productReview.update(content);

        return productReview.getId();
    }
}
