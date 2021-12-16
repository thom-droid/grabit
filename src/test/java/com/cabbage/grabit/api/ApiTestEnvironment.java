package com.cabbage.grabit.api;

import com.cabbage.grabit.api.product.ProductService;
import com.cabbage.grabit.api.product_review.ProductReviewService;
import com.cabbage.grabit.api.shipment.RegionService;
import com.cabbage.grabit.api.taker.TakerService;
import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.product.dto.ProductPostRequestDto;
import com.cabbage.grabit.domain.product_review.ProductReviewRepository;
import com.cabbage.grabit.domain.reply.ReplyRepository;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.shipment.RegionRepository;
import com.cabbage.grabit.domain.subscription.SubscriptionRepository;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.domain.user.TakerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTestEnvironment {

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mvc;

    @LocalServerPort
    protected int port;

    @Autowired
    protected GiverRepository giverRepository;

    @Autowired
    protected RegionRepository regionRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected TakerRepository takerRepository;

    @Autowired
    protected TakerService takerService;

    @Autowired
    protected ProductService productService;

    @Autowired
    protected RegionService regionService;

    @Autowired
    protected ProductReviewService productReviewService;

    @Autowired
    protected ProductReviewRepository productReviewRepository;

    @Autowired
    protected SubscriptionRepository subscriptionRepository;

    @Autowired
    protected ReplyRepository replyRepository;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Before
    public void populateProduct() throws Exception{
        // given
        Giver giver = giverRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("no giver found"));

        Category category = Category.FOOD_MEAL_KIT;
        String name = "인도카레의냄새";
        Integer price = 15000;
        String image = "curry.jpg";
        String details = "치킨이 들어있스빈다.";

        Set<Region> regionSet = new HashSet<>(regionRepository.findAll());

        ProductPostRequestDto requestDto = ProductPostRequestDto.builder()
                .category(category)
                .name(name)
                .price(price)
                .image(image)
                .details(details)
                .giver(giver)
                .regions(regionSet)
                .build();

        String json = new ObjectMapper().writeValueAsString(requestDto);
        String url = "http://localhost:"+port+"/api/v1/products";

        // when
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk()).andDo(print());
    }
}
