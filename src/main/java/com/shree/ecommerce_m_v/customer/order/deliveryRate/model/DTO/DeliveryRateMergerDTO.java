package com.shree.ecommerce_m_v.customer.order.deliveryRate.model.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRateMergerDTO {

    private Long deliveryRateId;
    private String city;
}
