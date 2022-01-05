package com.cabbage.grabit.api;

import com.cabbage.grabit.api.ApiTestEnvironment;
import com.cabbage.grabit.api.product.ProductFacade;
import com.cabbage.grabit.api.product_review.ProductReviewControllerTest;
import com.cabbage.grabit.api.product_review.ProductReviewFacade;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.dto.request.ProductPostRequestDto;
import com.cabbage.grabit.domain.product_review.dto.request.ReviewPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ProductExceptionTest extends ProductReviewControllerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private ProductFacade productFacade;

    @Autowired
    private ProductReviewFacade productReviewFacade;

    private final ProductReviewFacade mockReviewFacade = Mockito.mock((ProductReviewFacade.class));

    @Test
    @Transactional
    public void givenWrongGiver_whenGetProductDetail_thenNotBelongException(){

        ProductFacade mockProductFacade = Mockito.mock((ProductFacade.class));
        Mockito.when(mockProductFacade.getProductDetailToGiver(12L, 2L))
                .thenThrow(new ApiException(ApiStatus.PRODUCT_NOT_BELONG_TO_GIVER));

        exception.expect(ApiException.class);

        productFacade.getProductDetailToGiver(12L, 2L);

    }

    @Test
    @Transactional
    public void givenGiverNotSubscribed_whenPostReview_thenReviewNotSubscribedException(){

        //given
        Taker taker = takerService.getTakerById(3L);
        Product product = productService.getProductById(1L);
        ReviewPostRequestDto requestDto = ReviewPostRequestDto.builder()
                .rate(4)
                .content("dd")
                .product(product)
                .taker(taker)
                .build();

        ReviewPostRequestDto mockRequest = Mockito.mock(ReviewPostRequestDto.class);
        Mockito.when(mockReviewFacade.postReview(mockRequest)).thenThrow(new ApiException(ApiStatus.REVIEW_NOT_SUBSCRIBED));

        exception.expect(ApiException.class);

        productReviewFacade.postReview(requestDto);

    }

}
