package com.cabbage.grabit.web;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.domain.product.dto.PostProductRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductApiControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Bean
    public Giver dummyGiver(){

        return Giver.builder()
                .name("할명수")
                .company("무한상사")
                .email("audtn@gmail.com")
                .businessNum("1234523422")
                .picture("default.jpg")
                .build();
    }

    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GiverRepository giverRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        }

    @After
    public void tearDown() throws Exception {
        productRepository.deleteAll();
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

        Giver mergedGiver = giverRepository.save(giver);
//        Giver giver = giverRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("no"));
        String name = "글쓰기의 요소";
        Integer price = 8000;
        String details = "글쓰기가 무엇인지 보여드립니다.";
        String image = "image.jpg";

        PostProductRequestDto requestDto =
                PostProductRequestDto.builder()
                .giver(giver)
                .name(name)
                .details(details)
                .price(price)
                .category(Category.CLOTHING)
                .image(image)
                .build();

        String url ="http://localhost:"+port+ "/api/v1/products";
        String content = new ObjectMapper().writeValueAsString(requestDto);

        //given
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andExpect(status().isOk()).andDo(print());

        //then
        List<Product> productList = productRepository.findAll();

        assertEquals(productList.get(0).getName(), name);
        assertEquals(productList.get(0).getPrice(), price);
        assertEquals(productList.get(0).getDetails(), details);
        assertEquals(productList.get(0).getGiver().getId(), mergedGiver.getId());
        System.out.println(mergedGiver.getProductList());

    }

    @Test
    public void givenProductId_whenPutRequest_thenIsStatusSwitched() throws Exception{

        long updateId = 1L;

        String url ="http://localhost:"+port+"/api/v1/products/"+updateId;

        // when
        mockMvc.perform(put(url)).andExpect(status().isOk());

        //then
        List<Product> productList = productRepository.findAll();
        Product product = productList.get(0);

        assertThat(product.isSaleStatus()).isEqualTo(false);

    }

    @Test(expected = IllegalArgumentException.class)
    public void givenProductId_whenDeleteRequest_thenDeleted() throws Exception{

        long deleteId = 1L;

        String url ="http://localhost:"+port+"/api/v1/products/"+deleteId;

        //when
        mockMvc.perform(delete(url)).andExpect(status().isOk());

        //then
        Product deletedProduct = productRepository.findById(deleteId).orElseThrow(()-> new IllegalArgumentException("error"));
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
        Category category = Category.CLOTHING;
        String url = "http://localhost:"+port+"/api/v1/products?page="+page+"&category="+ category;

        //when
        mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(print());

    }


}