package com.cabbage.grabit.domain.product.support;

import com.cabbage.grabit.domain.product.Category;
import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.product.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.Assert;

import javax.annotation.Nullable;

import java.io.ObjectStreamField;
import java.util.Arrays;

import static com.cabbage.grabit.domain.product.QProduct.product;

public class ProductExpression {


    //    @QueryDelegate(Product.class)
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
