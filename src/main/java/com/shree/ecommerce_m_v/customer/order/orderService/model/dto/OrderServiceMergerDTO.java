package com.shree.ecommerce_m_v.customer.order.orderService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceMergerDTO {

    private Long orderServiceId;
    private String street;
}
