package com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.dto;

import com.shree.ecommerce_m_v.vendor.service.service.service.dto.ServiceMergerDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceCategory.service.dto.ServiceCategoryMergerDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.dto.ServiceLocationMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProviderDTO {

    private Long serviceProviderId;
    private VendorMergerDTO vendorMergerDTO;

    private ServiceCategoryMergerDTO serviceCategoryMergerDTO;

    private List<ServiceMergerDTO> serviceMergerDTOS= new ArrayList<>();

    private List<ServiceLocationMergerDTO> serviceLocationMergerDTOS= new ArrayList<>();

}
