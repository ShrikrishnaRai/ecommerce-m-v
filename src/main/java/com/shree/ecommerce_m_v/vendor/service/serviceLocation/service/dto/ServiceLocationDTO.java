package com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.dto;

import com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.dto.ServiceProviderMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceLocationDTO {

    private Long serviceLocationId;
    private String city;

    private ServiceProviderMergerDTO serviceProviderMergerDTO;
}
