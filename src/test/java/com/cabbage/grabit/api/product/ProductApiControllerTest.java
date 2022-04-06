package com.cabbage.grabit.api.product;

import com.cabbage.grabit.api.ApiTestEnvironment;
import com.cabbage.grabit.domain.user.Giver;
import com.cabbage.grabit.exception.ApiException;
import com.cabbage.grabit.exception.ApiStatus;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ProductApiControllerTest extends ApiTestEnvironment {

    @Test
    @Transactional(readOnly = true)
    public void whenProductNotBelongToGiver_thenIsApiExceptionInvoked() throws Exception{
        //given
        Giver giver = giverService.findById(2L);
        Long productId = 12L;
        String url = "http://localhost:" +port +"/api/v1/products/giver/"+ productId;

        Throwable thrown = catchThrowable(() -> {throw new ApiException(ApiStatus.PRODUCT_NOT_BELONG_TO_GIVER);});

        //when
        mvc.perform(get(url).param("giverId", "2"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ApiException))
                .andExpect(result -> assertThat(ApiStatus.PRODUCT_NOT_BELONG_TO_GIVER.getMessage()).isEqualTo(result.getResolvedException().getMessage()))
                .andDo(print());


    }
}