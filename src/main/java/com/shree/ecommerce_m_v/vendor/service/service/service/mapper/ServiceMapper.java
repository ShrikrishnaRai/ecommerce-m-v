package com.shree.ecommerce_m_v.vendor.service.service.service.mapper;

import com.shree.ecommerce_m_v.vendor.service.service.service.dto.ServiceDTO;
import com.shree.ecommerce_m_v.vendor.service.service.model.entity.ServiceEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.dto.ServiceProviderMergerDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceReview.service.dto.ServiceReviewMergerDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceReview.model.entity.ServiceReviewEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ServiceMapper {

    protected ServiceEntity toEntity(ServiceDTO serviceDTO) {
        return ServiceEntity.builder()
                .serviceId(serviceDTO.getServiceId())
                .serviceName(serviceDTO.getServiceName())
                .description(serviceDTO.getDescription())
                .parentServiceId(serviceDTO.getParentServiceId())
                .slug(serviceDTO.getSlug())
                .build();
    }

    protected ServiceDTO toDTO(ServiceEntity serviceEntity) {
        ServiceProviderMergerDTO serviceProviderMergerDTO= new ServiceProviderMergerDTO();
        if(serviceEntity.getServiceProviderEntity()!=null){
            serviceProviderMergerDTO= ServiceProviderMergerDTO.builder()
                    .serviceProviderId(serviceEntity.getServiceProviderEntity().getServiceProviderId())
                    .build();
        }

        List<ServiceReviewMergerDTO> serviceReviewMergerDTOS= new ArrayList<>();
        if(serviceEntity.getServiceReviewEntities().size()!=0){
            for(ServiceReviewEntity serviceReviewEntity:serviceEntity.getServiceReviewEntities())
            serviceReviewMergerDTOS.add(ServiceReviewMergerDTO.builder()
                    .serviceReviewId(serviceReviewEntity.getServiceReviewId())
                    .rating(serviceReviewEntity.getRating())
                    .build());
        }

        return ServiceDTO.builder()
                .serviceId(serviceEntity.getServiceId())
                .serviceName(serviceEntity.getServiceName())
                .description(serviceEntity.getDescription())
                .parentServiceId(serviceEntity.getParentServiceId())
                .slug(serviceEntity.getSlug())
                .serviceProviderMergerDTO(serviceProviderMergerDTO)
                .serviceReviewMergerDTOList(serviceReviewMergerDTOS)
                .build();
    }

    protected List<ServiceDTO> toDTOs(List<ServiceEntity> serviceEntities) {
        return serviceEntities.stream()
                .map(serviceEntity -> toDTO(serviceEntity))
                .collect(Collectors.toList());
    }
}
