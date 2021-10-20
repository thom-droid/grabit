package com.cabbage.grabit.service.products;

import com.cabbage.grabit.domain.products.Categories;
import com.cabbage.grabit.domain.products.Products;
import com.cabbage.grabit.domain.products.ProductsRepository;
import com.cabbage.grabit.domain.user.Giver;

import com.cabbage.grabit.domain.user.GiverRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsServiceTest {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private GiverRepository giverRepository;


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
}