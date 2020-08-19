package com.shree.ecommerce_m_v.vendor.service.serviceCategory.service.mapper;

import com.shree.ecommerce_m_v.vendor.service.serviceCategory.service.dto.ServiceCategoryDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceCategory.model.entity.ServiceCategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ServiceCategoryMapper {

    protected ServiceCategoryEntity toEntity(ServiceCategoryDTO serviceCategoryDTO) {

        return ServiceCategoryEntity.builder()
                .serviceCategoryId(serviceCategoryDTO.getServiceCategoryId())
                .serviceCategoryName(serviceCategoryDTO.getServiceCategoryName())
                .serviceDescription(serviceCategoryDTO.getServiceDescription())
                .serviceSlug(serviceCategoryDTO.getServiceSlug())
                .build();
    }

    protected ServiceCategoryDTO toDTO(ServiceCategoryEntity serviceCategoryEntity) {

        return ServiceCategoryDTO.builder()
                .serviceCategoryId(serviceCategoryEntity.getServiceCategoryId())
                .serviceCategoryName(serviceCategoryEntity.getServiceCategoryName())
                .serviceDescription(serviceCategoryEntity.getServiceDescription())
                .serviceSlug(serviceCategoryEntity.getServiceSlug())
                .build();
    }

    protected List<ServiceCategoryDTO> toDTOs(List<ServiceCategoryEntity> serviceCategoryEntities) {
        return serviceCategoryEntities.stream()
                .map(serviceCategoryEntity -> toDTO(serviceCategoryEntity))
                .collect(Collectors.toList());
    }

}
