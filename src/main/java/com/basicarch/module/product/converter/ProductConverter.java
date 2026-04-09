package com.basicarch.module.product.converter;

import com.basicarch.base.converter.TypeConverter;
import com.basicarch.module.product.entity.Product;
import com.basicarch.module.product.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TypeConverter.class})
public interface ProductConverter {
    @Mapping(source = "createTime", target = "createTime", qualifiedByName = "localDateTimeToString")
    @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = "localDateTimeToString")
    ProductModel toModel(Product entity);

    @Mapping(target = "rowNum", ignore = true)
    @Mapping(source = "createTime", target = "createTime", qualifiedByName = "stringToLocalDateTime")
    @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = "stringToLocalDateTime")
    Product toEntity(ProductModel model);

    List<ProductModel> toModelList(List<Product> list);

    List<Product> toEntityList(List<ProductModel> list);
}
