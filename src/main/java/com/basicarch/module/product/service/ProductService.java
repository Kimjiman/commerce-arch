package com.basicarch.module.product.service;

import com.basicarch.base.service.BaseService;
import com.basicarch.module.product.entity.Product;
import com.basicarch.module.product.model.ProductSearchParam;
import com.basicarch.module.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService implements BaseService<Product, ProductSearchParam, Long> {
    private final ProductRepository productRepository;

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllBy(ProductSearchParam param) {
        return productRepository.findAllBy(param);
    }

    @Override
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public Product update(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) return;
        productRepository.deleteById(id);
    }
}
