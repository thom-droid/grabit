package com.cabbage.grabit.service.products;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.user.Giver;

import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.domain.product.dto.response.ProductResponseDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

    private String name = "12가지인생의법칙";
    private Integer price = 10000;
    private String details = "혼돈의해독제랍니다";
    private String image = "image";

    @Before
    public void setupDummy(){
        Giver giver = Giver.builder()
                .name("할명수")
                .company("무한상사")
                .email("audtn@gmail.com")
                .businessNum("1234523422")
                .picture("default.jpg")
                .build();

        Giver mergedGiver = giverRepository.save(giver);

        Product product1 = Product.builder()
                .giver(mergedGiver)
                .details(details)
                .category(Category.CLOTHING)
                .image(image)
                .name(name)
                .price(price)
                .build();

        mergedGiver.getProductList().add(product1);

        productRepository.save(product1);

    }

    @After
    public void cleanup(){
        productRepository.deleteAll();
    }

    @Test
    @Transactional
    public void postTest(){

        Giver giver = giverRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("no found"));


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
        String image = "image2";

        Giver giver = giverRepository.findById(1L).orElseThrow(()->new IllegalArgumentException("none"));

        Product product2 = Product.builder()
                .name(name2)
                .price(price2)
                .details(details2)
                .image(image)
                .giver(giver)
                .category(Category.CLOTHING)
                .build();
        giver.getProductList().add(product2);
        // save
        productRepository.save(product2);

        List<Product> productList = giver.getProductList();

        productList.forEach(p -> {
            System.out.println(p.toString());
                }
        );
        assertThat(productList.get(0).getName()).isEqualTo(name);
        assertThat(productList.get(1).getName()).isEqualTo(name2);


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
        List<ProductResponseDto> responseDtoList = giverEntity.getProductList().stream().map(ProductResponseDto::from).collect(Collectors.toList());

        for (ProductResponseDto dto : responseDtoList){
            System.out.println(dto.getName());
        }

        assertThat(responseDtoList).isNotNull();

    }


}