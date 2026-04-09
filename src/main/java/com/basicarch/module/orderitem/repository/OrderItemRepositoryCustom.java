package com.basicarch.module.orderitem.repository;

import com.basicarch.module.orderitem.entity.OrderItem;
import com.basicarch.module.orderitem.model.OrderItemSearchParam;

import java.util.List;

public interface OrderItemRepositoryCustom {
    List<OrderItem> findAllBy(OrderItemSearchParam param);
}
