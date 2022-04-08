package com.cabbage.grabit.domain.product.support;

import com.cabbage.grabit.api.product.ProductApiControllerTest;
import com.cabbage.grabit.domain.product.dto.response.ProductListResponseDto;
import com.cabbage.grabit.domain.product.dto.response.ProductListResponseToGiverDto;
import com.cabbage.grabit.util.SearchParam;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductQuerySupportTest extends ProductApiControllerTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private ProductQuerySupport productQuerySupport;

    @Test
    @Transactional
    public void findProductById() throws Exception {
//        save();

        ProductQuerySupport querySupport = new ProductQuerySupport(new JPAQueryFactory(em));

        querySupport.findProducts().forEach(p -> System.out.println(p.toString()));

    }

    @Test
    @Transactional
    public void findProductsPaginatedWithParam() {
        SearchParam param = SearchParam.builder()
                .keyword("레")
                .category("CLOTHING")
                .pageable(PageRequest.of(0,5)).build();

        Page<ProductListResponseDto> list = productQuerySupport.findProductsPaginatedWithParam(param);

        assertThat(list).isNotEmpty();
        list.getContent().forEach(p -> System.out.println(p.toString()));

    }

    @Test
    @Transactional
    public void findProductsPaginatedWithoutParam(){
        SearchParam param = SearchParam.builder()
                .pageable(PageRequest.of(0,5, Sort.by("price").ascending()))
                .sort("price")
                .build();
        System.out.println(param.getPageable().getOffset());
        System.out.println(param.getPageable().getPageSize());
        System.out.println(param.getPageable().getSort());
        System.out.println(param.getPageable().getPageNumber());

        Page<ProductListResponseDto> list = productQuerySupport.findProductsPaginatedWithParam(param);

        assertThat(list).isNotEmpty();
        list.getContent().forEach(p -> System.out.println(p.toString()));
    }

    @Test
    @Transactional(readOnly = true)
    public void findProductsByDate() {

        LocalDate startDate = LocalDate.of(2021,10,5);
        LocalDate endDate = LocalDate.now();
        Long giverId = 1L;

        Page<ProductListResponseToGiverDto> list = productQuerySupport.findProductsByDate(giverId, startDate);
        assertThat(list).isNotEmpty();
        for (ProductListResponseToGiverDto responseDto : list.getContent()) {
            System.out.println(responseDto.toString());
        }

    }
}