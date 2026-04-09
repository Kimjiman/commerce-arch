package com.basicarch.module.payment.model;

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
public class PaymentModel extends BaseModel<Long> {
    private Long orderId;
    private Long amount;
    private String method;
    private String status;
    private String paidAt;
}
