package com.cabbage.grabit.service.products;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.user.Giver;

import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.domain.product.dto.PostProductRequestDto;
import com.cabbage.grabit.web.dto.response.ProductResponseDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductJPATest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GiverRepository giverRepository;

//    private Giver giver = Giver.builder()
//            .name("할명수")
//            .company("무한상사")
//            .email("audtn@gmail.com")
//            .businessNum("1234523422")
//            .picture("default.jpg")
//            .build();

    private String name = "글쓰기의 요소";
    private Integer price = 8000;
    private String details = "글쓰기가 무엇인지 보여드립니다.";

//    @Before
//    public void setupDummy(){
//
//        giverRepository.save(giver);
//
//        Product product1 = PostProductRequestDto.builder()
//                .giver(giver)
//                .categories(Categories.CLOTHING)
//                .details(details)
//                .price(price)
//                .name(name)
//                .build().toEntity();
//
//        giver.addProduct(product1);
//
//        productsRepository.save(product1);
//
//    }

    @After
    public void cleanup(){
        productRepository.deleteAll();
    }

    @Test
    public void givenProduct_thenIsLongReturned() {

        List<Product> productList = productRepository.findAll();

        assertEquals(productList.get(0).getName(), name);
        assertEquals(productList.get(0).getPrice(), price);
        assertEquals(productList.get(0).getDetails(), details);

    }


    @Test
    public void selectProductListByGiverTest(){

        // second product
        String name2 = "돈키호테";
        Integer price2 = 9500;
        String details2 = "눈물없인 볼 수 없다";
        Giver giver = giverRepository.findById(1L).orElseThrow(()->new IllegalArgumentException("none"));
        Product product2 = PostProductRequestDto.builder()
                .giver(giver)
                .name(name2)
                .price(price2)
                .category(Category.CLOTHING_SOCKS)
                .details(details2)
                .build().toEntity();
        // save
        productRepository.save(product2);

        Giver giverEntity = giverRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("error"));
        //giverEntity.addProduct(product2);

        List<Product> productList = giverEntity.getProductList();
        List<ProductResponseDto> list = new ArrayList<>();

        for(Product product : productList){
            list.add(new ProductResponseDto(product));
        }

        assertThat(list.get(0).getName()).isEqualTo(name);
        assertThat(list.get(1).getName()).isEqualTo(name2);

    }

    @Test
    public void whenReferencingSideIsChanged_thenOwningSideAlsoChanges(){

        Giver giverEntity = giverRepository.findById(1L).orElseThrow(()->new IllegalArgumentException("none"));;
        giverEntity.updatePicture("myeongsu.jpg");

        Product product = productRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("none"));
        assertThat(product.getGiver().getPicture()).isEqualTo(product.getGiver().getPicture());

    }

    @Test
    public void selectProductsUsingStream() {

        Giver giverEntity = giverRepository.getOne(1L);
        List<ProductResponseDto> responseDtoList = giverEntity.getProductList().stream().map(ProductResponseDto::new).collect(Collectors.toList());

        for (ProductResponseDto dto : responseDtoList){
            System.out.println(dto.getName());
        }

        assertThat(responseDtoList).isNotNull();

    }


}