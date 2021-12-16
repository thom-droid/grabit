package com.cabbage.grabit.api.product_review;

import com.cabbage.grabit.api.ApiTestEnvironment;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.product_review.dto.ReviewPostRequestDto;
import com.cabbage.grabit.domain.reply.Reply;
import com.cabbage.grabit.domain.reply.dto.ReplyPostRequestDto;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.Taker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

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
    public void whenReviewPostedTwice_thenIsRateUpdated() throws Exception{

        // review with rating 4
        postProductReview();

        // given
        int rate = 2;
        String content = "카레에 건더기가 없어요";
        Product product = productService.getProductById(12L);
        Taker taker = takerService.getTakerById(4L);

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
        System.out.println(product.getProductStat().toString());
        assertThat(product.getProductStat().getRate()).isEqualTo(3);
        assertThat(product.getProductStat().getReviewCount()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void whenSameReplyPostedTwice_thenExceptionReturned() throws Exception{

        //FIXME 현재 이 테스트는 의도대로 apiException이 발생하므로 통과하지 않음. thrown 됐을 때 정상적으로 통과되도록 수정 필요
        postProductReview();
        postProductReview();
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

    @Test
    @Transactional
    public void postReply() throws Exception{
        //given
        postProductReview();

        String content = "카레맛있지요?";
        Giver giver = giverRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("no giver found"));
        ProductReview productReview = productReviewService.findById(1L);
        ReplyPostRequestDto requestDto =
                ReplyPostRequestDto.builder()
                .content(content)
                .giver(giver)
                .productReview(productReview)
                .build();

        String url = "http://localhost:"+port+"/reply";
        String json = new ObjectMapper().writeValueAsString(requestDto);

        // when
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk()).andDo(print());
        System.out.println(json);
        // then
        Reply reply = replyRepository.findById(1L).orElseThrow(()->new IllegalArgumentException("nothing"));
        assertThat(content).isEqualTo(reply.getContent());
        assertNotNull(reply.getGiver());
        assertNotNull(reply.getProductReview());
        assertThat(productReview.getReply().getContent()).isEqualTo(content);
        System.out.println(productReview.getReply().getContent());
        System.out.println(productReview.getContent());
    }
}