package com.basicarch.module.product.repository;

import com.basicarch.base.utils.StringUtils;
import com.basicarch.module.product.entity.Product;
import com.basicarch.module.product.entity.QProduct;
import com.basicarch.module.product.model.ProductSearchParam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findAllBy(ProductSearchParam param) {
        QProduct q = QProduct.product;
        return queryFactory.selectFrom(q)
                .where(buildWhere(param, q))
                .orderBy(q.id.asc())
                .fetch();
    }

    private BooleanBuilder buildWhere(ProductSearchParam param, QProduct q) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.isNotBlank(param.getName())) {
            builder.and(q.name.contains(param.getName()));
        }
        return builder;
    }
}
