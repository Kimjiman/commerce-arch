package com.basicarch.module.product.controller;

import com.basicarch.module.product.facade.ProductFacade;
import com.basicarch.module.product.model.ProductModel;
import com.basicarch.module.product.model.ProductSearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductFacade productFacade;

    @GetMapping
    public List<ProductModel> selectList(ProductSearchParam param) {
        return productFacade.findAllBy(param);
    }

    @GetMapping("/{id}")
    public ProductModel selectById(@PathVariable Long id) {
        return productFacade.findById(id);
    }

    @PostMapping
    public void create(@RequestBody ProductModel model) {
        productFacade.create(model);
    }

    @PutMapping
    public void update(@RequestBody ProductModel model) {
        productFacade.update(model);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productFacade.deleteById(id);
    }
}
