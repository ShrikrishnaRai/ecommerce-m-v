package com.shree.ecommerce_m_v.customer.order.orderStatus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDTO {

    private long orderStatusId;
    private String orderName;


}
