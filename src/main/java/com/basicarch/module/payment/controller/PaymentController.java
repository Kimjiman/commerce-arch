package com.basicarch.module.payment.controller;

import com.basicarch.module.payment.facade.PaymentFacade;
import com.basicarch.module.payment.model.PaymentModel;
import com.basicarch.module.payment.model.PaymentSearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentFacade paymentFacade;

    @GetMapping
    public List<PaymentModel> selectList(PaymentSearchParam param) { return paymentFacade.findAllBy(param); }

    @GetMapping("/{id}")
    public PaymentModel selectById(@PathVariable Long id) { return paymentFacade.findById(id); }

    @PostMapping
    public void create(@RequestBody PaymentModel model) { paymentFacade.create(model); }

    @PutMapping
    public void update(@RequestBody PaymentModel model) { paymentFacade.update(model); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { paymentFacade.deleteById(id); }
}
