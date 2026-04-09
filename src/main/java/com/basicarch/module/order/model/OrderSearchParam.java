package com.basicarch.module.order.model;

import com.basicarch.base.model.BaseSearchParam;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class OrderSearchParam extends BaseSearchParam<Long> {
    // TODO: add search fields
}
