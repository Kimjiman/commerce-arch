package com.basicarch.module.order.repository;

import com.basicarch.module.order.entity.Order;
import com.basicarch.module.order.model.OrderSearchParam;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findAllBy(OrderSearchParam param);
}
