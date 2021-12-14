package com.cabbage.grabit.api.product_review;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.product_review.ProductReviewRepository;
import com.cabbage.grabit.domain.product_review.dto.ReviewPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;

    public ProductReview findById(Long id){
        return productReviewRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("no review found"));
    }

    @Transactional
    public Long postReview(Taker taker,
                           Product product,
                           ReviewPostRequestDto requestDto){

        ProductReview productReview = productReviewRepository.save(ProductReview.create(taker, product, requestDto));

        return productReview.getId();
    }

    @Transactional
    public Long updateReview(String content, Long reviewId){

        ProductReview productReview = findById(reviewId);
        productReview.update(content);

        return productReview.getId();
    }
}
