package com.basicarch.module.orderitem.converter;

import com.basicarch.base.converter.TypeConverter;
import com.basicarch.module.orderitem.entity.OrderItem;
import com.basicarch.module.orderitem.model.OrderItemModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TypeConverter.class})
public interface OrderItemConverter {
    @Mapping(source = "createTime", target = "createTime", qualifiedByName = "localDateTimeToString")
    @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = "localDateTimeToString")
    OrderItemModel toModel(OrderItem entity);

    @Mapping(target = "rowNum", ignore = true)
    @Mapping(source = "createTime", target = "createTime", qualifiedByName = "stringToLocalDateTime")
    @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = "stringToLocalDateTime")
    OrderItem toEntity(OrderItemModel model);

    List<OrderItemModel> toModelList(List<OrderItem> list);
    List<OrderItem> toEntityList(List<OrderItemModel> list);
}
