package com.basicarch.module.payment.model;

import com.basicarch.base.model.BaseSearchParam;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSearchParam extends BaseSearchParam<Long> {
    // TODO: add search fields
}
