package com.basicarch.module.orderitem.repository;

import com.basicarch.module.orderitem.entity.OrderItem;
import com.basicarch.module.orderitem.entity.QOrderItem;
import com.basicarch.module.orderitem.model.OrderItemSearchParam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<OrderItem> findAllBy(OrderItemSearchParam param) {
        QOrderItem q = QOrderItem.orderItem;
        return queryFactory.selectFrom(q)
                .where(buildWhere(param, q))
                .orderBy(q.id.asc())
                .fetch();
    }

    private BooleanBuilder buildWhere(OrderItemSearchParam param, QOrderItem q) {
        BooleanBuilder builder = new BooleanBuilder();
        // TODO: add search conditions
        return builder;
    }
}
