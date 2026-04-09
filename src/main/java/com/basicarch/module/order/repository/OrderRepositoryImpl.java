package com.basicarch.module.order.repository;

import com.basicarch.module.order.entity.Order;
import com.basicarch.module.order.entity.QOrder;
import com.basicarch.module.order.model.OrderSearchParam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findAllBy(OrderSearchParam param) {
        QOrder q = QOrder.order;
        return queryFactory.selectFrom(q)
                .where(buildWhere(param, q))
                .orderBy(q.id.asc())
                .fetch();
    }

    private BooleanBuilder buildWhere(OrderSearchParam param, QOrder q) {
        BooleanBuilder builder = new BooleanBuilder();
        // TODO: add search conditions
        return builder;
    }
}
