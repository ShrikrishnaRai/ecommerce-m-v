package com.shree.ecommerce_m_v.customer.order.orderService.model.dto;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceDTO {

    private long orderServiceId;
    private String street;
    private String city;
    private String district;
    private String state;
    private LocalDate serviceDate;
    private LocalTime serviceTime;

    private CustomerMergerDTO customerMergerDTO;

}
