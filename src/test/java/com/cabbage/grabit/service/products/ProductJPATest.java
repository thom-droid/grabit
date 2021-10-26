package com.cabbage.grabit.service.products;

import com.cabbage.grabit.domain.products.Categories;
import com.cabbage.grabit.domain.products.Products;
import com.cabbage.grabit.domain.products.ProductsRepository;
import com.cabbage.grabit.domain.user.Giver;

import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.web.dto.request.PostProductRequestDto;
import com.cabbage.grabit.web.dto.response.ProductResponseDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductJPATest {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private GiverRepository giverRepository;

    private Giver giver = Giver.builder()
            .name("할명수")
            .company("무한상사")
            .email("audtn@gmail.com")
            .businessNum("1234523422")
            .picture("default.jpg")
            .build();

    private String name = "글쓰기의 요소";
    private Integer price = 8000;
    private String details = "글쓰기가 무엇인지 보여드립니다.";

    @Before
    public void setupDummy(){

        giverRepository.save(giver);

        Products product1 = PostProductRequestDto.builder()
                .giver(giver)
                .categories(Categories.CLOTHING)
                .details(details)
                .price(price)
                .name(name)
                .build().toEntity();

        giver.addProduct(product1);

        productsRepository.save(product1);

    }

    @After
    public void cleanup(){
        productsRepository.deleteAll();
    }

    @Test
    public void givenProduct_thenIsLongReturned() {

        List<Products> productsList = productsRepository.findAll();

        assertEquals(productsList.get(0).getName(), name);
        assertEquals(productsList.get(0).getPrice(), price);
        assertEquals(productsList.get(0).getDetails(), details);

    }


    @Test
    public void selectProductListByGiverTest(){

        // second product
        String name2 = "돈키호테";
        Integer price2 = 9500;
        String details2 = "눈물없인 볼 수 없다";

        Products product2 = PostProductRequestDto.builder()
                .giver(giver)
                .name(name2)
                .price(price2)
                .categories(Categories.CLOTHING_SOCKS)
                .details(details2)
                .build().toEntity();
        // save
        productsRepository.save(product2);

        Giver giverEntity = giverRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("error"));
        giverEntity.addProduct(product2);

        List<Products> productsList = giverEntity.getProducts();
        List<ProductResponseDto> list = new ArrayList<>();

        for(Products products:productsList){
            list.add(new ProductResponseDto(products));
        }

        assertThat(list.get(0).getName()).isEqualTo(name);
        assertThat(list.get(1).getName()).isEqualTo(name2);

    }

    @Test
    public void whenReferencingSideIsChanged_thenOwningSideAlsoChanges(){

        Giver giverEntity = giverRepository.getOne(1L);
        giverEntity.updatePicture("myeongsu.jpg");

        Products product = productsRepository.getOne(1L);
        assertThat(giver.getPicture()).isEqualTo(product.getGiver().getPicture());

    }

    @Test
    public void selectProductsUsingStream() {

        Giver giverEntity = giverRepository.getOne(1L);
        List<ProductResponseDto> responseDtoList = giverEntity.getProducts().stream().map(ProductResponseDto::new).collect(Collectors.toList());

        for (ProductResponseDto dto : responseDtoList){
            System.out.println(dto.getName());
        }

        assertThat(responseDtoList).isNotNull();

    }


}