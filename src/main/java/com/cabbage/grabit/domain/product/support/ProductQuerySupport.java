package com.cabbage.grabit.domain.product.support;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.ProductStat;
import com.cabbage.grabit.domain.product.dto.response.ProductListResponseDto;
import com.cabbage.grabit.domain.user.dto.response.GiverResponseForProduct;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.cabbage.grabit.domain.product.QProduct.product;
import static com.cabbage.grabit.domain.user.QGiver.giver;

@RequiredArgsConstructor
@Repository
public class ProductQuerySupport {

    private final JPAQueryFactory queryFactory;

    public List<ProductListResponseDto> findProducts(){

        return queryFactory.select(
                    Projections.constructor(
                            ProductListResponseDto.class,
                            product.id,
                            product.name,
                            product.price,
                            product.details,
                            product.saleStatus,
                            product.createdDate,
                            product.category,
                            Projections.constructor(
                                    ProductStat.class,
                                    product.productStat.subscriptionCount,
                                    product.productStat.rate,
                                    product.productStat.reviewCount,
                                    product.productStat.five,
                                    product.productStat.four,
                                    product.productStat.three,
                                    product.productStat.two,
                                    product.productStat.one
                            ),
                            Projections.constructor(
                                    GiverResponseForProduct.class,
                                    product.giver.id,
                                    product.giver.businessNum,
                                    product.giver.company,
                                    product.giver.picture
                            )
                    )
                )
                .from(product)
                .join(product.giver, giver)
                .fetch();

    }


}
