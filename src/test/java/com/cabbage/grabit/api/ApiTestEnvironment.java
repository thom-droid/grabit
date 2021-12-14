package com.cabbage.grabit.api;

import com.cabbage.grabit.api.product.ProductService;
import com.cabbage.grabit.api.product_review.ProductReviewService;
import com.cabbage.grabit.api.shipment.RegionService;
import com.cabbage.grabit.api.taker.TakerService;
import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.product_review.ProductReviewRepository;
import com.cabbage.grabit.domain.shipment.RegionRepository;
import com.cabbage.grabit.domain.subscription.SubscriptionRepository;
import com.cabbage.grabit.domain.user.GiverRepository;
import com.cabbage.grabit.domain.user.TakerRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
}
