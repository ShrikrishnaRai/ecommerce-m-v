package com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceLocationMergerDTO {

    private Long serviceLocationId;
    private String city;
}
