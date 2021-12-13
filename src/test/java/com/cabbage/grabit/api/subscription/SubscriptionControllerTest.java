package com.cabbage.grabit.api.subscription;

import com.cabbage.grabit.api.ApiTestEnvironment;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.shipment.Region;
import com.cabbage.grabit.domain.shipment.RegionRepository;
import com.cabbage.grabit.domain.shipment.ShippingAddress;
import com.cabbage.grabit.domain.shipment.ShippingStatus;
import com.cabbage.grabit.domain.shipment.dto.ShippingAddressPostRequestDto;
import com.cabbage.grabit.domain.subscription.dto.SubscriptionListResponseDto;
import com.cabbage.grabit.domain.subscription.dto.SubscriptionPostRequestDto;
import com.cabbage.grabit.domain.user.Taker;
import com.cabbage.grabit.domain.user.TakerRepository;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubscriptionControllerTest extends ApiTestEnvironment {


    @Autowired
    private SubscriptionService subscriptionService;


    @Test
    @Transactional
    public void getSubscription() throws Exception {

        Long takerId = 3L;
        String url = "http://localhost:" +port+"/api/v1/subscription/list/"+takerId;

        mvc.perform(get(url)).andExpect(status().isOk()).andDo(print());


    }

    @Test
    @Transactional
    public void postSubscription() throws Exception{

        // when
        Taker taker = takerService.getTakerById(3L);
        Product product = productService.getProductById(3L);
        Region region = regionService.getRegionById(1L);
        ShippingAddress addressDto = ShippingAddress.builder()
                .addressDetail("test")
                .isDefault(true)
                .nickname("home")
                .recipient("bang")
                .region(region)
                .taker(taker)
                .build();

        SubscriptionPostRequestDto requestDto = SubscriptionPostRequestDto.builder()
                .taker(taker)
                .product(product)
                .shippingAddress(addressDto)
                .price(5000)
                .recipient("bang")
                .shippingMessage("thanks")
                .shippingStatus(ShippingStatus.PREPARING)
                .build();

//        private ShippingStatus shippingStatus;
//        private Integer price;
//        private String shippingMessage;
//        private String recipient;
//        private Taker taker;
//        private Product product;
//        private ShippingAddress shippingAddress;


        String url = "http://localhost:"+port+"/api/v1/subscription";
//        ObjectMapper om = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, )
        String requestJson = new ObjectMapper().writeValueAsString(requestDto);

        //when

        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)).andExpect(status().isOk()).andDo(print());
    }
}