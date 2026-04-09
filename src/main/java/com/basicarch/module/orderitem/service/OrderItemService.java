package com.basicarch.module.orderitem.service;

import com.basicarch.base.service.BaseService;
import com.basicarch.module.orderitem.entity.OrderItem;
import com.basicarch.module.orderitem.model.OrderItemSearchParam;
import com.basicarch.module.orderitem.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Slf4j @RequiredArgsConstructor
public class OrderItemService implements BaseService<OrderItem, OrderItemSearchParam, Long> {
    private final OrderItemRepository orderItemRepository;

    @Override
    public boolean existsById(Long id) {
        return orderItemRepository.existsById(id);
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public List<OrderItem> findAllBy(OrderItemSearchParam param) {
        return orderItemRepository.findAllBy(param);
    }

    @Override
    public OrderItem save(OrderItem entity) {
        return orderItemRepository.save(entity);
    }

    @Override
    public OrderItem update(OrderItem entity) {
        return orderItemRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) return;
        orderItemRepository.deleteById(id);
    }
}
