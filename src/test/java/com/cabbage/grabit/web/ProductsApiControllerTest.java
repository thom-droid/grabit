package com.cabbage.grabit.web;

import com.cabbage.grabit.domain.products.Categories;
import com.cabbage.grabit.domain.products.Products;
import com.cabbage.grabit.domain.products.ProductsRepository;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.web.dto.request.PostProductRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductsApiControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private GiverRepository giverRepository;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @After
    public void tearDown() throws Exception {
        productsRepository.deleteAll();
    }

    @Test
    public void givenProducts_whenPostMapping_thenPostProduct() throws Exception{

        //given
        Giver giver = Giver.builder()
                .name("할명수")
                .company("무한상사")
                .email("audtn@gmail.com")
                .businessNum("1234523422")
                .picture("default.jpg")
                .build();

        giverRepository.save(giver);

        String name = "글쓰기의 요소";
        Integer price = 8000;
        String details = "글쓰기가 무엇인지 보여드립니다.";

        PostProductRequestDto requestDto =
                PostProductRequestDto.builder()
                .giver(giver)
                .name(name)
                .details(details)
                .price(price)
                .categories(Categories.CLOTHING)
                .build();

        String url ="http://localhost:"+port+ "/api/v1/products";
        String content = new ObjectMapper().writeValueAsString(requestDto);
        System.out.println(content);
        //given
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andExpect(status().isOk());

        //then
        List<Products> productsList = productsRepository.findAll();

        assertEquals(productsList.get(0).getGiver(), giver);
        assertEquals(productsList.get(0).getName(), name);
        assertEquals(productsList.get(0).getPrice(), price);
        assertEquals(productsList.get(0).getDetails(), details);

    }

    @Test
    public void givenProductId_whenPutRequest_thenIsStatusSwitched() throws Exception{

        // given
        // data
        Giver giver = Giver.builder()
                .name("할명수")
                .company("무한상사")
                .email("audtn@gmail.com")
                .businessNum("1234523422")
                .picture("default.jpg")
                .build();

        giverRepository.save(giver);

        String name = "글쓰기의 요소";
        Integer price = 8000;
        String details = "글쓰기가 무엇인지 보여드립니다.";

        Products productEntity = PostProductRequestDto.builder()
                .giver(giver)
                .name(name)
                .price(price)
                .details(details)
                .categories(Categories.CLOTHING)
                .build().toEntity();

        // insert data
        productsRepository.save(productEntity);

        Long updateId = productEntity.getId();

        String url ="http://localhost:"+port+"/api/v1/products/"+updateId;

        // when
        mockMvc.perform(put(url)).andExpect(status().isOk());

        //then
        List<Products> productsList = productsRepository.findAll();
        Products product = productsList.get(0);

        assertThat(product.isSaleStatus()).isEqualTo(false);

    }

    @Test
    public void givenProductId_whenDeleteRequest_thenDeleted() throws Exception{

        // given
        // data
        Giver giver = Giver.builder()
                .name("할명수")
                .company("무한상사")
                .email("audtn@gmail.com")
                .businessNum("1234523422")
                .picture("default.jpg")
                .build();

        giverRepository.save(giver);

        String name = "글쓰기의 요소";
        Integer price = 8000;
        String details = "글쓰기가 무엇인지 보여드립니다.";

        Products productEntity = PostProductRequestDto.builder()
                .giver(giver)
                .name(name)
                .price(price)
                .details(details)
                .categories(Categories.CLOTHING)
                .build().toEntity();

        // insert data
        productsRepository.save(productEntity);

        Long deleteId = productEntity.getId();

        String url ="http://localhost:"+port+"/api/v1/products/"+deleteId;

        //when
        mockMvc.perform(delete(url)).andExpect(status().isOk());

        //then
        List<Products> list = productsRepository.findAll();
        assertThat(list).isEmpty();
        assertThat(giver).isNotNull();

    }
}