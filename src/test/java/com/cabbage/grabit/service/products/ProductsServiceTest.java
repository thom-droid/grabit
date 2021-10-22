package com.cabbage.grabit.service.products;

import com.cabbage.grabit.domain.products.Categories;
import com.cabbage.grabit.domain.products.Products;
import com.cabbage.grabit.domain.products.ProductsRepository;
import com.cabbage.grabit.domain.user.Giver;

import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.web.dto.request.PostProductRequestDto;
import com.cabbage.grabit.web.dto.response.ProductResponseDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsServiceTest {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private GiverRepository giverRepository;

    @Autowired
    private ProductsService productsService;


    @After
    public void cleanup(){
        productsRepository.deleteAll();
    }

    @Test
    public void givenProduct_thenIsLongReturned() {


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

        productsRepository.save(Products.builder()
                .giver(giver)
                .name(name)
                .price(price)
                .details(details)
                .categories(Categories.CLOTHING)
                .build());


        List<Products> productsList = productsRepository.findAll();

        assertEquals(productsList.get(0).getGiver(), giver);
        assertEquals(productsList.get(0).getName(), name);
        assertEquals(productsList.get(0).getPrice(), price);
        assertEquals(productsList.get(0).getDetails(), details);

    }

    @Test
    public void productStatusSwitchTest(){

        // given data
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

        // update status
        productsService.switchStatus(1L);

        List<Products> productsList = productsRepository.findAll();
        Products product = productsList.get(0);

        assertThat(product.isSaleStatus()).isEqualTo(false);

        productsService.switchStatus(1L);

        List<Products> productsList2 = productsRepository.findAll();
        Products product2 = productsList2.get(0);

        System.out.println(product2.isSaleStatus());

        assertThat(product2.isSaleStatus()).isEqualTo(true);
    }

    @Test
    public void selectProductListByGiverTest(){

        // given
        // giver
        Giver giver = Giver.builder()
                .name("할명수")
                .company("무한상사")
                .email("audtn@gmail.com")
                .businessNum("1234523422")
                .picture("default.jpg")
                .build();

        giverRepository.save(giver);

        // first product
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

        // save
        productsRepository.save(productEntity);

        // second product
        String name2 = "돈키호테";
        Integer price2 = 9500;
        String details2 = "눈물없인 볼 수 없다";

        Products productEntity2 = PostProductRequestDto.builder()
                .giver(giver)
                .name(name2)
                .price(price2)
                .categories(Categories.CLOTHING_SOCKS)
                .details(details2)
                .build().toEntity();
        // save
        productsRepository.save(productEntity2);

        List<ProductResponseDto> list = productsService.selectProductListByGiver(giver);

        assertThat(list).isNotNull();
        assertThat(list.get(0).getName()).isEqualTo(name);
        assertThat(list.get(1).getName()).isEqualTo(name2);


    }
}