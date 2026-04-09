package com.basicarch.module.payment.repository;

import com.basicarch.module.payment.entity.Payment;
import com.basicarch.module.payment.model.PaymentSearchParam;

import java.util.List;

public interface PaymentRepositoryCustom {
    List<Payment> findAllBy(PaymentSearchParam param);
}
