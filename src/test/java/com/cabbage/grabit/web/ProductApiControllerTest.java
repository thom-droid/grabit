package com.cabbage.grabit.web;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.shipment.RegionRepository;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.domain.product.dto.request.ProductPostRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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


import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductApiControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    EntityManager em;

    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GiverRepository giverRepository;

    @Autowired
    private RegionRepository regionRepository;

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
    @Transactional
    public void givenProducts_whenPostMapping_thenPostProduct() throws Exception{

        //given

        //giver
        Giver giver = Giver.builder()
                .name("할명수")
                .company("무한상사")
                .email("audtn@gmail.com")
                .businessNum("1234523422")
                .picture("default.jpg")
                .build();

        giver = giverRepository.save(giver);

        // product
        String name = "글쓰기의 요소";
        Integer price = 8000;
        String details = "글쓰기가 무엇인지 보여드립니다.";
        String image = "image.jpg";

        // region
        Set<Region> regionSet = new HashSet<>();
        Region region = new Region();
        region.setSido("서울");
        region.setGugun("마포구");
        Region region1 = new Region();
        region1.setSido("마포대교는");
        region1.setGugun("무너졌냐 이 시끼야");

        regionSet.add(region);
        regionSet.add(region1);

        regionRepository.save(region);
        regionRepository.save(region1);

        ProductPostRequestDto requestDto =
                ProductPostRequestDto.builder()
                .giver(giver)
                .name(name)
                .details(details)
                .price(price)
                .category(Category.CLOTHING)
                .image(image)
                .regions(regionSet)
                .build();

        String url ="http://localhost:"+port+ "/api/v1/products";
        String content = new ObjectMapper().writeValueAsString(requestDto);

        //given
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andExpect(status().isOk()).andDo(print());

        //then
        List<Product> productList = productRepository.findAll();
        List<Giver> givers = giverRepository.findAll();
        givers.forEach(g -> System.out.println(g.getId()));
        Giver giver1 = giverRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("nn"));

        System.out.println("is loaded " +em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(giver1));

        assertEquals(productList.get(0).getName(), name);
        assertEquals(productList.get(0).getPrice(), price);
        assertEquals(productList.get(0).getDetails(), details);
        assertEquals(productList.get(0).getGiver().getId(), giver.getId());
        productList.get(0).getRegions().forEach(r ->{
            System.out.println(r.getSido());
            System.out.println(r.getGugun());
        });
        System.out.println(giver1.getProductList().get(0).getName());
        System.out.println(giver==giver1);

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

//        Giver giverEntity = giverRepository.findById(1L).orElseThrow(()->new IllegalArgumentException("no"));
//        Long giverId = giverEntity.getId();
//        System.out.println(giverId);
//        System.out.println(giverEntity.getName());
        String url = "http://localhost:"+ port +"/api/v1/products/giver/1";

        //when
        mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void whenGetRequest_thenIsProductPaged() throws Exception {

//        //giver
//        Giver giver = Giver.builder()
//                .name("할명수")
//                .company("무한상사")
//                .email("audtn@gmail.com")
//                .businessNum("1234523422")
//                .picture("default.jpg")
//                .build();
//
//        giver = giverRepository.save(giver);
//
//        // product
//        String name = "글쓰기의 요소";
//        Integer price = 8000;
//        String details = "글쓰기가 무엇인지 보여드립니다.";
//        String image = "image.jpg";
//
//        // region
//        Set<Region> regionSet = new HashSet<>();
//        Region region = new Region();
//        region.setSido("서울");
//        region.setGugun("마포구");
//        Region region1 = new Region();
//        region1.setSido("마포대교는");
//        region1.setGugun("무너졌냐 이 시끼야");
//
//        region = regionRepository.save(region);
//        region1 = regionRepository.save(region1);
//
//        Product product1 =
//                Product.builder()
//                        .giver(giver)
//                        .name(name)
//                        .details(details)
//                        .price(price)
//                        .category(Category.CLOTHING)
//                        .image(image)
//                        .saleStatus(true)
//                        .build();
//
//        product1.getRegions().addAll(regionSet);
////        product1.getRegions().add(region1);
//
//        Product product2 =
//                Product.builder()
//                .giver(giver)
//                .image(image)
//                .details(details)
//                .price(price)
//                .name(name)
//                .category(Category.FOOD)
//                .saleStatus(false)
//                .build();
//
//        product2.getRegions().addAll(regionSet);
////        product2.getRegions().add(region1);
//
//        productRepository.save(product1);
//        productRepository.save(product2);

        int page = 1;

        Category category = Category.CLOTHING;
        String url = "http://localhost:"+port+"/api/v1/products?page="+page+"&category="+ category;

        //when
        mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(print());

    }


}