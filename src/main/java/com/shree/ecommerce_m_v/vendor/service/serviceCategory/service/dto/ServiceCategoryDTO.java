package com.shree.ecommerce_m_v.vendor.service.serviceCategory.service.dto;

import com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.dto.ServiceProviderMergerDTO;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ServiceCategoryDTO {

    private long serviceCategoryId;
    private String serviceCategoryName;
    private String serviceDescription;
    private String serviceSlug;

    private ServiceProviderMergerDTO serviceProviderMergerDTO;

}
