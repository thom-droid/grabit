package com.cabbage.grabit.api.giver;

import com.cabbage.grabit.api.ApiTestEnvironment;
import com.cabbage.grabit.domain.product.dto.response.ProductListResponseToGiverDto;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class GiverServiceTest extends ApiTestEnvironment {

    @Test
    public void searchByDate() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        Long giverId = 1L;
        Pageable pageable = PageRequest.of(0,5);
        Page<ProductListResponseToGiverDto> list = giverService.searchByDate(giverId, date);
        assertThat(list.getContent()).isNotEmpty();

    }
}