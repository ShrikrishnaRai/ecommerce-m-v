package com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.mapper;

import com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.dto.ServiceLocationDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceLocation.model.entity.ServiceLocationEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.dto.ServiceProviderMergerDTO;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ServiceLocationMapper {

    protected ServiceLocationDTO toDTO(ServiceLocationEntity serviceLocationEntity){

        ServiceProviderMergerDTO serviceProviderMergerDTO= new ServiceProviderMergerDTO();
        if(serviceLocationEntity.getServiceProviderEntity()!=null){
            serviceProviderMergerDTO=ServiceProviderMergerDTO.builder()
                    .serviceProviderId(serviceLocationEntity.getServiceProviderEntity().getServiceProviderId())
                    .build();
        }

        return ServiceLocationDTO.builder()
                .serviceLocationId(serviceLocationEntity.getServiceLocationId())
                .city(serviceLocationEntity.getCity())
                .serviceProviderMergerDTO(serviceLocationEntity.getServiceProviderEntity()!=null?serviceProviderMergerDTO :null)
                .build();
    }

    protected List<ServiceLocationDTO>  toDTOs(List<ServiceLocationEntity> serviceLocationEntities){
        return serviceLocationEntities.stream()
                .map(serviceLocationEntity -> toDTO(serviceLocationEntity))
                .collect(Collectors.toList());
    }


    protected ServiceLocationEntity toEntity(ServiceLocationDTO serviceLocationDTO){

        return ServiceLocationEntity.builder()
                .city(serviceLocationDTO.getCity())
                .build();
    }
}
