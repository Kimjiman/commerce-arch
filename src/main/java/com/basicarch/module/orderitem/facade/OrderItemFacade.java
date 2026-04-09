package com.basicarch.module.orderitem.facade;

import com.basicarch.base.annotation.Facade;
import com.basicarch.base.exception.SystemErrorCode;
import com.basicarch.base.exception.ToyAssert;
import com.basicarch.module.orderitem.converter.OrderItemConverter;
import com.basicarch.module.orderitem.model.OrderItemModel;
import com.basicarch.module.orderitem.model.OrderItemSearchParam;
import com.basicarch.module.orderitem.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j @Facade @RequiredArgsConstructor
public class OrderItemFacade {
    private final OrderItemService orderItemService;
    private final OrderItemConverter orderItemConverter;

    public List<OrderItemModel> findAllBy(OrderItemSearchParam param) {
        return orderItemConverter.toModelList(orderItemService.findAllBy(param));
    }

    public OrderItemModel findById(Long id) {
        ToyAssert.notNull(id, SystemErrorCode.REQUIRED, "ID is required.");
        return orderItemService.findById(id).map(orderItemConverter::toModel).orElse(null);
    }

    public void create(OrderItemModel model) {
        orderItemService.save(orderItemConverter.toEntity(model));
    }

    public void update(OrderItemModel model) {
        ToyAssert.notNull(model.getId(), SystemErrorCode.REQUIRED, "ID is required.");
        orderItemService.update(orderItemConverter.toEntity(model));
    }

    @Transactional
    public void deleteById(Long id) {
        ToyAssert.notNull(id, SystemErrorCode.REQUIRED, "ID is required.");
        orderItemService.deleteById(id);
    }
}
