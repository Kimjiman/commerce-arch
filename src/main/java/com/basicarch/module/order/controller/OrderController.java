package com.basicarch.module.order.controller;

import com.basicarch.module.order.facade.OrderFacade;
import com.basicarch.module.order.model.OrderModel;
import com.basicarch.module.order.model.OrderSearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderFacade orderFacade;

    @GetMapping
    public List<OrderModel> selectList(OrderSearchParam param) { return orderFacade.findAllBy(param); }

    @GetMapping("/{id}")
    public OrderModel selectById(@PathVariable Long id) { return orderFacade.findById(id); }

    @PostMapping
    public void create(@RequestBody OrderModel model) { orderFacade.create(model); }

    @PutMapping
    public void update(@RequestBody OrderModel model) { orderFacade.update(model); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { orderFacade.deleteById(id); }
}
