package com.basicarch.module.payment.repository;

import com.basicarch.module.payment.entity.Payment;
import com.basicarch.module.payment.entity.QPayment;
import com.basicarch.module.payment.model.PaymentSearchParam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Payment> findAllBy(PaymentSearchParam param) {
        QPayment q = QPayment.payment;
        return queryFactory.selectFrom(q)
                .where(buildWhere(param, q))
                .orderBy(q.id.asc())
                .fetch();
    }

    private BooleanBuilder buildWhere(PaymentSearchParam param, QPayment q) {
        BooleanBuilder builder = new BooleanBuilder();
        // TODO: add search conditions
        return builder;
    }
}
