package com.basicarch.module.product.repository;

import com.basicarch.module.product.entity.Product;
import com.basicarch.module.product.model.ProductSearchParam;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findAllBy(ProductSearchParam param);
}
