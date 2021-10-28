package com.cabbage.grabit.web;

import com.cabbage.grabit.domain.products.Categories;
import com.cabbage.grabit.domain.products.Products;
import com.cabbage.grabit.domain.products.ProductsRepository;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.service.products.ProductsService;
import com.cabbage.grabit.web.dto.request.PostProductRequestDto;
import com.cabbage.grabit.web.dto.response.ProductResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        MockitoAnnotations.initMocks(this);
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

        //given
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andExpect(status().isOk());

        //then
        List<Products> productsList = productsRepository.findAll();

        assertEquals(productsList.get(0).getName(), name);
        assertEquals(productsList.get(0).getPrice(), price);
        assertEquals(productsList.get(0).getDetails(), details);

    }

    @Test
    public void givenProductId_whenPutRequest_thenIsStatusSwitched() throws Exception{

        long updateId = 1L;

        String url ="http://localhost:"+port+"/api/v1/products/"+updateId;

        // when
        mockMvc.perform(put(url)).andExpect(status().isOk());

        //then
        List<Products> productsList = productsRepository.findAll();
        Products product = productsList.get(0);

        assertThat(product.isSaleStatus()).isEqualTo(false);

    }

    @Test(expected = IllegalArgumentException.class)
    public void givenProductId_whenDeleteRequest_thenDeleted() throws Exception{

        long deleteId = 1L;

        String url ="http://localhost:"+port+"/api/v1/products/"+deleteId;

        //when
        mockMvc.perform(delete(url)).andExpect(status().isOk());

        //then
        Products deletedProduct = productsRepository.findById(deleteId).orElseThrow(()-> new IllegalArgumentException("error"));
    }

    @Test
    @Transactional
    public void givenGiver_whenGetRequest_thenListIsReturned() throws Exception{

        Giver giverEntity = giverRepository.findById(1L).orElseThrow(()->new IllegalArgumentException("no"));
        Long giverId = giverEntity.getId();
        System.out.println(giverId);
        System.out.println(giverEntity.getName());
        String url = "http://localhost:"+ port +"/api/v1/giver/products/"+giverId;

        //when
        mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void whenGetRequest_thenIsProductPaged() throws Exception {

        int page = 1;
        String url = "http://localhost:"+port+"/api/v1/products?page="+page;

        //when
        mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(print());

    }


}