package com.shree.ecommerce_m_v.customer.deliveryAddress.model.DTO;

import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressDTO {

    private Long deliveryAddressId;
    private String street;
    private String city;
    private String district;
    private String state;
    private String contactNo;

    private OrderMergerDTO orderMergerDTO;
}
