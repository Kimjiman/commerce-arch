package com.basicarch.module.product.model;

import com.basicarch.base.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductModel extends BaseModel<Long> {
    private String name;
    private Long price;
    private Integer stockQty;
    private String status;
}
