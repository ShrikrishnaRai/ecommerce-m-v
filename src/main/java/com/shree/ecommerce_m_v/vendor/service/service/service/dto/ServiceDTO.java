package com.shree.ecommerce_m_v.vendor.service.service.service.dto;

import com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.dto.ServiceProviderMergerDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceReview.service.dto.ServiceReviewMergerDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {

    private long serviceId;
    private String serviceName;
    private String description;
    private Long parentServiceId;
    private String slug;

    private ServiceProviderMergerDTO serviceProviderMergerDTO;

    private List<ServiceReviewMergerDTO> serviceReviewMergerDTOList= new ArrayList<>();


}
