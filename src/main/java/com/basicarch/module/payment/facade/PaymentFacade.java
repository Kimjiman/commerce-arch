package com.basicarch.module.payment.facade;

import com.basicarch.base.annotation.Facade;
import com.basicarch.base.exception.SystemErrorCode;
import com.basicarch.base.exception.ToyAssert;
import com.basicarch.module.payment.converter.PaymentConverter;
import com.basicarch.module.payment.model.PaymentModel;
import com.basicarch.module.payment.model.PaymentSearchParam;
import com.basicarch.module.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j @Facade @RequiredArgsConstructor
public class PaymentFacade {
    private final PaymentService paymentService;
    private final PaymentConverter paymentConverter;

    public List<PaymentModel> findAllBy(PaymentSearchParam param) {
        return paymentConverter.toModelList(paymentService.findAllBy(param));
    }

    public PaymentModel findById(Long id) {
        ToyAssert.notNull(id, SystemErrorCode.REQUIRED, "ID is required.");
        return paymentService.findById(id).map(paymentConverter::toModel).orElse(null);
    }

    public void create(PaymentModel model) {
        paymentService.save(paymentConverter.toEntity(model));
    }

    public void update(PaymentModel model) {
        ToyAssert.notNull(model.getId(), SystemErrorCode.REQUIRED, "ID is required.");
        paymentService.update(paymentConverter.toEntity(model));
    }

    @Transactional
    public void deleteById(Long id) {
        ToyAssert.notNull(id, SystemErrorCode.REQUIRED, "ID is required.");
        paymentService.deleteById(id);
    }
}
