package com.shree.ecommerce_m_v.customer.order.deliveryRate.model.DTO;

import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRateDTO {

    private Long deliveryId;
    private String city;
    private double rate;

    private OrderMergerDTO orderMergerDTO;
}
