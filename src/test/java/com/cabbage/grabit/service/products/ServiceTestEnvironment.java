package com.cabbage.grabit.service.products;

import com.cabbage.grabit.api.product.ProductFacade;
import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
public class ServiceTestEnvironment {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GiverRepository giverRepository;

    @Autowired
    ProductFacade productFacade;

    private String name = "12가지인생의법칙";
    private Integer price = 8000;
    private String details = "글쓰기가 무엇인지 보여드립니다.";
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

    @Test
    public void doSomething(){
        System.out.println("do something");
    }

}
