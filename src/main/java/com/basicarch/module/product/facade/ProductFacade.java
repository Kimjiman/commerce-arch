package com.basicarch.module.product.facade;

import com.basicarch.base.annotation.Facade;
import com.basicarch.base.exception.SystemErrorCode;
import com.basicarch.base.exception.ToyAssert;
import com.basicarch.module.product.converter.ProductConverter;
import com.basicarch.module.product.model.ProductModel;
import com.basicarch.module.product.model.ProductSearchParam;
import com.basicarch.module.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Facade
@RequiredArgsConstructor
public class ProductFacade {
    private final ProductService productService;
    private final ProductConverter productConverter;

    public List<ProductModel> findAllBy(ProductSearchParam param) {
        return productConverter.toModelList(productService.findAllBy(param));
    }

    public ProductModel findById(Long id) {
        ToyAssert.notNull(id, SystemErrorCode.REQUIRED, "ID is required.");
        return productService.findById(id).map(productConverter::toModel).orElse(null);
    }

    public void create(ProductModel model) {
        ToyAssert.notBlank(model.getName(), SystemErrorCode.REQUIRED, "name is required.");
        productService.save(productConverter.toEntity(model));
    }

    public void update(ProductModel model) {
        ToyAssert.notNull(model.getId(), SystemErrorCode.REQUIRED, "ID is required.");
        productService.update(productConverter.toEntity(model));
    }

    @Transactional
    public void deleteById(Long id) {
        ToyAssert.notNull(id, SystemErrorCode.REQUIRED, "ID is required.");
        productService.deleteById(id);
    }
}
