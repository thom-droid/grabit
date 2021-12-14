package com.cabbage.grabit.api.product_review;

import com.cabbage.grabit.api.ApiTestEnvironment;
import com.cabbage.grabit.api.product.ProductApiControllerTest;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.product_review.dto.ReviewPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductReviewControllerTest extends ApiTestEnvironment {

    @Test
    @Transactional
    public void postProductReview() throws Exception{


        // given
        int rate = 4;
        String content = "아주 맛이 좋네요";
        Product product = productService.getProductById(12L);
        Taker taker = takerService.getTakerById(3L);

        ReviewPostRequestDto requestDto = ReviewPostRequestDto.builder()
                .content(content)
                .product(product)
                .taker(taker)
                .rate(rate)
                .build();

        String json = new ObjectMapper().writeValueAsString(requestDto);
        String url = "http://localhost:" +port + "/review";

        //when
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk()).andDo(print());

        //then
        ProductReview productReview = productReviewService.findById(1L);
        System.out.println(productReview.toString());
        assertThat(productReview.getContent()).isEqualTo(content);
        assertThat(productReview.getRate()).isEqualByComparingTo(rate);
        assertThat(product.getProductStat().getFour()).isEqualTo(1);
        assertThat(product.getProductStat().getReviewCount()).isEqualTo(1);
        assertThat(product.getProductStat().getRate()).isEqualTo(4);
    }

    @Test
    @Transactional
    public void updateProductReview() throws Exception{

        // post a test review
        postProductReview();

        //given
        String content = "감자탕?";
        Long reviewId = 1L;

//        String json = new ObjectMapper().writeValueAsString(content);
        String url = "http://localhost:" +port + "/review/" +reviewId;

        //when
        mvc.perform(put(url).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        ProductReview productReview = productReviewService.findById(1L);
        if (productReview != null){
            System.out.println(productReview.toString());
        }
        //then
        assertThat(content).isEqualTo(productReview.getContent());
        assertTrue(productReview.getIsModified());
    }
}