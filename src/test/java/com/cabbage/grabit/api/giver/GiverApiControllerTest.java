package com.cabbage.grabit.api.giver;

import com.cabbage.grabit.api.ApiTestEnvironment;
import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.dto.request.ProductPostRequestDto;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.user.Giver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GiverApiControllerTest extends ApiTestEnvironment {

    @Test
    public void switchStatus() {
    }

    @Test
    @Transactional(readOnly = true)
    public void getMyProductDetail() throws Exception{

        //given
        Giver giver = giverService.findById(1L);
        Long productId = 12L;
        String url = "http://localhost:" +port +"/api/v1/giver/product/detail";
//        String json = new ObjectMapper().writeValueAsString(giver);

        //when
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.put("giverId", Collections.singletonList("1"));
        paramMap.put("productId", Collections.singletonList("12"));
        mvc.perform(get(url).params(paramMap)).andExpect(status().isOk()).andDo(print());
        Product product = productService.getProductById(12L);

        assertThat(productId).isEqualTo(product.getId());
        assertThat(giver).isEqualTo(product.getGiver());

    }

    @Test
    @Transactional
    public void save() throws Exception {
        // given
        Giver giver = giverRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("no giver found"));

        Category category = Category.FOOD_MEAL_KIT;
        String name = "인도카레의냄새";
        Integer price = 15000;
        String image = "curry.jpg";
        String details = "치킨이 들어있스빈다.";

        Set<Region> regionSet = new HashSet<>(regionRepository.findAll());

        ProductPostRequestDto requestDto = ProductPostRequestDto.builder()
                .category(category)
                .name(name)
                .price(price)
                .image(image)
                .details(details)
                .giver(giver)
                .regions(regionSet)
                .build();

        String json = new ObjectMapper().writeValueAsString(requestDto);
//        String url = "http://localhost:"+port+"/api/v1/products";
        String url = "http://localhost:"+port+"/api/v1/giver/save";

        // when
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk()).andDo(print());

        //then
        Product product1 = productRepository.findById(12L).orElseThrow(()->new IllegalArgumentException("nothing found"));
        assertThat(product1.getName()).isEqualTo(name);
    }
}