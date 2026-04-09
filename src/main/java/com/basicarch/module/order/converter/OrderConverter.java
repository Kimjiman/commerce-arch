package com.basicarch.module.order.converter;

import com.basicarch.base.converter.TypeConverter;
import com.basicarch.module.order.entity.Order;
import com.basicarch.module.order.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TypeConverter.class})
public interface OrderConverter {
    @Mapping(source = "createTime", target = "createTime", qualifiedByName = "localDateTimeToString")
    @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = "localDateTimeToString")
    OrderModel toModel(Order entity);

    @Mapping(target = "rowNum", ignore = true)
    @Mapping(source = "createTime", target = "createTime", qualifiedByName = "stringToLocalDateTime")
    @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = "stringToLocalDateTime")
    Order toEntity(OrderModel model);

    List<OrderModel> toModelList(List<Order> list);
    List<Order> toEntityList(List<OrderModel> list);
}
