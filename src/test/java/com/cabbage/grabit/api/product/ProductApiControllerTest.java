package com.cabbage.grabit.api.product;

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

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductApiControllerTest extends ApiTestEnvironment {

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
        String url = "http://localhost:"+port+"/api/v1/products";

        // when
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk()).andDo(print());

        //then
        Product product1 = productRepository.findById(12L).orElseThrow(()->new IllegalArgumentException("nothing found"));
        assertThat(product1.getName()).isEqualTo(name);
    }
}