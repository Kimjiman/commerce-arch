package com.basicarch.module.payment.converter;

import com.basicarch.base.converter.TypeConverter;
import com.basicarch.module.payment.entity.Payment;
import com.basicarch.module.payment.model.PaymentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TypeConverter.class})
public interface PaymentConverter {
    @Mapping(source = "createTime", target = "createTime", qualifiedByName = "localDateTimeToString")
    @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = "localDateTimeToString")
    PaymentModel toModel(Payment entity);

    @Mapping(target = "rowNum", ignore = true)
    @Mapping(source = "createTime", target = "createTime", qualifiedByName = "stringToLocalDateTime")
    @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = "stringToLocalDateTime")
    Payment toEntity(PaymentModel model);

    List<PaymentModel> toModelList(List<Payment> list);
    List<Payment> toEntityList(List<PaymentModel> list);
}
