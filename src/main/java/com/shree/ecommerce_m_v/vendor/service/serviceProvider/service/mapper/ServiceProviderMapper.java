package com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.mapper;

import com.shree.ecommerce_m_v.vendor.service.service.service.dto.ServiceMergerDTO;
import com.shree.ecommerce_m_v.vendor.service.service.model.entity.ServiceEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceCategory.service.dto.ServiceCategoryMergerDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceCategory.model.entity.ServiceCategoryEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.dto.ServiceLocationMergerDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceLocation.model.entity.ServiceLocationEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.dto.ServiceProviderDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceProvider.model.entity.ServiceProviderEntity;
import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ServiceProviderMapper {

    protected ServiceProviderEntity toEntity(ServiceProviderDTO serviceProviderDTO){

        VendorEntity vendorEntity= new VendorEntity();
        if(serviceProviderDTO.getVendorMergerDTO()!=null){
            vendorEntity= VendorEntity.builder()
                    .vendorId(serviceProviderDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(serviceProviderDTO.getVendorMergerDTO().getVendorName())
                    .build();
        }

        ServiceCategoryEntity serviceCategoryEntity= new ServiceCategoryEntity();
        if(serviceProviderDTO.getServiceCategoryMergerDTO()!=null){
            serviceCategoryEntity=ServiceCategoryEntity.builder()
                    .serviceCategoryId(serviceProviderDTO.getServiceCategoryMergerDTO().getServiceCategoryId())
                    .serviceCategoryName(serviceProviderDTO.getServiceCategoryMergerDTO().getServiceCategoryName())
                    .build();
        }

        List<ServiceLocationEntity> serviceLocationEntities= new ArrayList<>();
        if(serviceProviderDTO.getServiceLocationMergerDTOS().size()!=0){
            for(ServiceLocationMergerDTO serviceLocationMergerDTO:serviceProviderDTO.getServiceLocationMergerDTOS()){
                serviceLocationEntities.add(ServiceLocationEntity.builder()
                        .serviceLocationId(serviceLocationMergerDTO.getServiceLocationId())
                        .city(serviceLocationMergerDTO.getCity())
                        .build());
            }
        }

        List<ServiceEntity> serviceEntities= new ArrayList<>();
        if(serviceProviderDTO.getServiceMergerDTOS().size()!=0){
            for(ServiceMergerDTO serviceMergerDTO:serviceProviderDTO.getServiceMergerDTOS()){
                serviceEntities.add(ServiceEntity.builder()
                        .serviceId(serviceMergerDTO.getServiceId())
                        .serviceName(serviceMergerDTO.getServiceName())
                        .build());
            }
        }

        return ServiceProviderEntity.builder()
                .vendorEntity(serviceProviderDTO.getVendorMergerDTO()!=null? vendorEntity:null)
                .serviceCategoryEntity(serviceProviderDTO.getServiceCategoryMergerDTO()!=null?serviceCategoryEntity:null)
                .serviceEntities(serviceProviderDTO.getServiceMergerDTOS().size()!=0?serviceEntities:null)
                .serviceLocationEntities(serviceProviderDTO.getServiceLocationMergerDTOS().size()!=0?serviceLocationEntities:null)
                .build();

    }

    protected ServiceProviderDTO toDTO(ServiceProviderEntity serviceProviderEntity){

        VendorMergerDTO vendorMergerDTO = new VendorMergerDTO();
        if(serviceProviderEntity.getVendorEntity()!=null){
            vendorMergerDTO= VendorMergerDTO.builder()
                    .vendorId(serviceProviderEntity.getVendorEntity().getVendorId())
                    .vendorName(serviceProviderEntity.getVendorEntity().getVendorName())
                    .build();
        }

        ServiceCategoryMergerDTO serviceCategoryMergerDTO= new ServiceCategoryMergerDTO();
        if(serviceProviderEntity.getServiceCategoryEntity()!=null){
            serviceCategoryMergerDTO=ServiceCategoryMergerDTO.builder()
                    .serviceCategoryId(serviceProviderEntity.getServiceCategoryEntity().getServiceCategoryId())
                    .serviceCategoryName(serviceProviderEntity.getServiceCategoryEntity().getServiceCategoryName())
                    .build();
        }

        List<ServiceLocationMergerDTO> serviceLocationMergerDTOS= new ArrayList<>();
        if(serviceProviderEntity.getServiceLocationEntities().size()!=0){
            for(ServiceLocationEntity serviceLocationEntity:serviceProviderEntity.getServiceLocationEntities()){
                serviceLocationMergerDTOS.add(ServiceLocationMergerDTO.builder()
                        .serviceLocationId(serviceLocationEntity.getServiceLocationId())
                        .city(serviceLocationEntity.getCity())
                        .build());
            }
        }

        List<ServiceMergerDTO> serviceMergerDTOS= new ArrayList<>();
        if(serviceProviderEntity.getServiceEntities().size()!=0){
            for(ServiceEntity serviceEntity:serviceProviderEntity.getServiceEntities()){
                serviceMergerDTOS.add(ServiceMergerDTO.builder()
                        .serviceId(serviceEntity.getServiceId())
                        .serviceName(serviceEntity.getServiceName())
                        .build());
            }
        }

        return ServiceProviderDTO.builder()
                .vendorMergerDTO(vendorMergerDTO)
                .serviceCategoryMergerDTO(serviceCategoryMergerDTO)
                .serviceLocationMergerDTOS(serviceLocationMergerDTOS)
                .serviceMergerDTOS(serviceMergerDTOS)
                .build();

    }

    protected List<ServiceProviderDTO> toDTOs(List<ServiceProviderEntity> serviceProviderEntities){
        return serviceProviderEntities.stream()
                .map(serviceProviderEntity -> toDTO(serviceProviderEntity))
                .collect(Collectors.toList());
    }
}
