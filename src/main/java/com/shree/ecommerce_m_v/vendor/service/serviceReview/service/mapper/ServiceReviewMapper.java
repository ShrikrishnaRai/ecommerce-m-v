package com.shree.ecommerce_m_v.vendor.service.serviceReview.service.mapper;

import com.shree.ecommerce_m_v.vendor.service.service.service.dto.ServiceMergerDTO;
import com.shree.ecommerce_m_v.vendor.service.service.model.entity.ServiceEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceReview.service.dto.ServiceReviewDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceReview.model.entity.ServiceReviewEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ServiceReviewMapper {

    protected ServiceReviewEntity toEntity(ServiceReviewDTO serviceReviewDTO) {
        ServiceEntity service = new ServiceEntity();
        if (serviceReviewDTO.getServiceMergerDTO() != null) {
            service = ServiceEntity.builder()
                    .serviceId(serviceReviewDTO.getServiceMergerDTO().getServiceId())
                    .serviceName(serviceReviewDTO.getServiceMergerDTO().getServiceName())
                    .build();
        }
        return ServiceReviewEntity.builder()
                .serviceReviewId(serviceReviewDTO.getServiceReviewId())
                .rating(serviceReviewDTO.getRating())
                .review(serviceReviewDTO.getReview())
                .reply(serviceReviewDTO.getReply())
                .service(serviceReviewDTO.getServiceMergerDTO()!=null ? service:null)
                .build();
    }

    protected ServiceReviewDTO toDTO(ServiceReviewEntity serviceReviewEntity) {
        ServiceMergerDTO serviceMergerDTO = new ServiceMergerDTO();
        if (serviceReviewEntity.getService() != null) {
            serviceMergerDTO = ServiceMergerDTO.builder()
                    .serviceId(serviceReviewEntity.getService().getServiceId())
                    .serviceName(serviceReviewEntity.getService().getServiceName())
                    .build();
        }
        return ServiceReviewDTO.builder()
                .serviceReviewId(serviceReviewEntity.getServiceReviewId())
                .rating(serviceReviewEntity.getRating())
                .review(serviceReviewEntity.getReview())
                .reply(serviceReviewEntity.getReply())
                .serviceMergerDTO(serviceMergerDTO)
                .build();
    }

    protected List<ServiceReviewDTO> toDTOs(List<ServiceReviewEntity> serviceReviewEntities) {
        return serviceReviewEntities.stream()
                .map(serviceReviewEntity -> toDTO(serviceReviewEntity))
                .collect(Collectors.toList());
    }
}
