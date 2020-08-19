package com.shree.ecommerce_m_v.customer.deliveryAddress.model.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddressMergerDTO {

    private Long deliveryAddressId;
    private String district;
}
