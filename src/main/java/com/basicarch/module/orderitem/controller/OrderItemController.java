package com.basicarch.module.orderitem.controller;

import com.basicarch.module.orderitem.facade.OrderItemFacade;
import com.basicarch.module.orderitem.model.OrderItemModel;
import com.basicarch.module.orderitem.model.OrderItemSearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemFacade orderItemFacade;

    @GetMapping
    public List<OrderItemModel> selectList(OrderItemSearchParam param) {
        return orderItemFacade.findAllBy(param);
    }

    @GetMapping("/{id}")
    public OrderItemModel selectById(@PathVariable Long id) {
        return orderItemFacade.findById(id);
    }

    @PostMapping
    public void create(@RequestBody OrderItemModel model) {
        orderItemFacade.create(model);
    }

    @PutMapping
    public void update(@RequestBody OrderItemModel model) {
        orderItemFacade.update(model);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        orderItemFacade.deleteById(id);
    }
}
