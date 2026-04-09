package com.basicarch.module.order.facade;

import com.basicarch.base.annotation.Facade;
import com.basicarch.base.exception.SystemErrorCode;
import com.basicarch.base.exception.ToyAssert;
import com.basicarch.module.order.converter.OrderConverter;
import com.basicarch.module.order.model.OrderModel;
import com.basicarch.module.order.model.OrderSearchParam;
import com.basicarch.module.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j @Facade @RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    public List<OrderModel> findAllBy(OrderSearchParam param) {
        return orderConverter.toModelList(orderService.findAllBy(param));
    }

    public OrderModel findById(Long id) {
        ToyAssert.notNull(id, SystemErrorCode.REQUIRED, "ID is required.");
        return orderService.findById(id).map(orderConverter::toModel).orElse(null);
    }

    public void create(OrderModel model) {
        orderService.save(orderConverter.toEntity(model));
    }

    public void update(OrderModel model) {
        ToyAssert.notNull(model.getId(), SystemErrorCode.REQUIRED, "ID is required.");
        orderService.update(orderConverter.toEntity(model));
    }

    @Transactional
    public void deleteById(Long id) {
        ToyAssert.notNull(id, SystemErrorCode.REQUIRED, "ID is required.");
        orderService.deleteById(id);
    }
}
