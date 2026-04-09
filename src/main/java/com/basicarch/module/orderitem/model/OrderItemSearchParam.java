package com.basicarch.module.orderitem.model;

import com.basicarch.base.model.BaseSearchParam;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class OrderItemSearchParam extends BaseSearchParam<Long> {
    // TODO: add search fields
}
