package com.shree.ecommerce_m_v.vendor.service.service.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceMergerDTO {

    private long serviceId;
    private String serviceName;


}
