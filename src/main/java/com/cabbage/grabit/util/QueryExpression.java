package com.cabbage.grabit.util;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.Expressions;

import static com.cabbage.grabit.domain.product.QProduct.product;

public class QueryExpression {

    public static BooleanBuilder isParamSpecified(SearchParam param){

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(param.getCategory()!=null){
            booleanBuilder.and(product.category.eq(Category.valueOf(param.getCategory())));
        }

        if(param.getKeyword()!=null){
            booleanBuilder.and(product.name.contains(param.getKeyword()));
        }

        return booleanBuilder;
    }

    public static OrderSpecifier<? extends Comparable> isOrderSpecified(String sort){

        Path<Object> searchSort = Expressions.path(Object.class, QProduct.product, sort);

        return new OrderSpecifier(Order.ASC, searchSort);
    }
}
