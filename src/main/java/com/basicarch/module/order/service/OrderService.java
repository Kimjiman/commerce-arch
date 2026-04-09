package com.basicarch.module.order.service;

import com.basicarch.base.service.BaseService;
import com.basicarch.module.order.entity.Order;
import com.basicarch.module.order.model.OrderSearchParam;
import com.basicarch.module.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Slf4j @RequiredArgsConstructor
public class OrderService implements BaseService<Order, OrderSearchParam, Long> {
    private final OrderRepository orderRepository;

    @Override
    public boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAllBy(OrderSearchParam param) {
        return orderRepository.findAllBy(param);
    }

    @Override
    public Order save(Order entity) {
        return orderRepository.save(entity);
    }

    @Override
    public Order update(Order entity) {
        return orderRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) return;
        orderRepository.deleteById(id);
    }
}
