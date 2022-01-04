package com.cabbage.grabit.domain.product.dto;

import com.cabbage.grabit.api.product.ProductApiControllerTest;
import com.cabbage.grabit.domain.product.support.ProductQuerySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductQuerySupportTest extends ProductApiControllerTest {

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void findProductById() throws Exception {
        save();

        ProductQuerySupport querySupport = new ProductQuerySupport(new JPAQueryFactory(em));

        querySupport.findProducts().forEach(p -> System.out.println(p.toString()));

    }
}