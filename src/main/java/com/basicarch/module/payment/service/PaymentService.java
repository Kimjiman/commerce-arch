package com.basicarch.module.payment.service;

import com.basicarch.base.service.BaseService;
import com.basicarch.module.payment.entity.Payment;
import com.basicarch.module.payment.model.PaymentSearchParam;
import com.basicarch.module.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Slf4j @RequiredArgsConstructor
public class PaymentService implements BaseService<Payment, PaymentSearchParam, Long> {
    private final PaymentRepository paymentRepository;

    @Override
    public boolean existsById(Long id) {
        return paymentRepository.existsById(id);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> findAllBy(PaymentSearchParam param) {
        return paymentRepository.findAllBy(param);
    }

    @Override
    public Payment save(Payment entity) {
        return paymentRepository.save(entity);
    }

    @Override
    public Payment update(Payment entity) {
        return paymentRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) return;
        paymentRepository.deleteById(id);
    }
}
