package com.basicarch.module.product.model;

import com.basicarch.base.model.BaseSearchParam;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchParam extends BaseSearchParam<Long> {
    private String name;
}
