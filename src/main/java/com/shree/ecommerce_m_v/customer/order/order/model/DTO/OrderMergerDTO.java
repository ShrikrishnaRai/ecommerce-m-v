package com.shree.ecommerce_m_v.customer.order.order.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMergerDTO {
    private Long id;
    private double totalAmount;


}
