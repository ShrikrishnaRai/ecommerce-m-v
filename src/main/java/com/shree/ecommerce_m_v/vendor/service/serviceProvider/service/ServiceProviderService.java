package com.shree.ecommerce_m_v.vendor.service.serviceProvider.service;

import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import com.shree.ecommerce_m_v.vendor.service.service.service.dto.ServiceMergerDTO;
import com.shree.ecommerce_m_v.vendor.service.service.model.entity.ServiceEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceCategory.model.entity.ServiceCategoryEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.dto.ServiceLocationMergerDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceLocation.model.entity.ServiceLocationEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.dto.ServiceProviderDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceProvider.model.entity.ServiceProviderEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceProvider.repository.ServiceProviderRepository;
import com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.mapper.ServiceProviderMapper;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServiceProviderService extends ServiceProviderMapper  {

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public String saveServiceProvider(ServiceProviderDTO serviceProviderDTO) {
        ServiceProviderEntity serviceProviderEntity= toEntity(serviceProviderDTO);
        serviceProviderRepository.saveAndFlush(serviceProviderEntity);
        return ResponseValue.SAVE_SUCCESS;
    }

    public ServiceProviderDTO updateServiceProvider(Long serviceProviderId, ServiceProviderDTO serviceProviderDTO) {

        ServiceProviderEntity serviceProviderEntity = serviceProviderRepository.getOne(serviceProviderId);


        if(serviceProviderDTO.getVendorMergerDTO()!=null){
            serviceProviderEntity.setVendorEntity(VendorEntity.builder()
                    .vendorId(serviceProviderDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(serviceProviderDTO.getVendorMergerDTO().getVendorName())
                    .build());
        }

        if(serviceProviderDTO.getServiceCategoryMergerDTO()!=null){
            serviceProviderEntity.setServiceCategoryEntity(ServiceCategoryEntity.builder()
                    .serviceCategoryId(serviceProviderDTO.getServiceCategoryMergerDTO().getServiceCategoryId())
                    .serviceCategoryName(serviceProviderDTO.getServiceCategoryMergerDTO().getServiceCategoryName())
                    .build());
        }

        List<ServiceLocationEntity> serviceLocationEntities= new ArrayList<>();
        if(serviceProviderDTO.getServiceLocationMergerDTOS().size()!=0){
            for(ServiceLocationMergerDTO serviceLocationMergerDTO:serviceProviderDTO.getServiceLocationMergerDTOS()){
                serviceLocationEntities.add(ServiceLocationEntity.builder()
                        .serviceLocationId(serviceLocationMergerDTO.getServiceLocationId())
                        .city(serviceLocationMergerDTO.getCity())
                        .build());
                serviceProviderEntity.setServiceLocationEntities(serviceLocationEntities);
            }
        }

        List<ServiceEntity> serviceEntities= new ArrayList<>();
        if(serviceProviderDTO.getServiceMergerDTOS().size()!=0) {
            for (ServiceMergerDTO serviceMergerDTO : serviceProviderDTO.getServiceMergerDTOS()) {
                serviceEntities.add(ServiceEntity.builder()
                        .serviceId(serviceMergerDTO.getServiceId())
                        .serviceName(serviceMergerDTO.getServiceName())
                        .build());
                serviceProviderEntity.setServiceEntities(serviceEntities);
            }
        }

        serviceProviderRepository.saveAndFlush(serviceProviderEntity);
        return toDTO(serviceProviderEntity);
    }

    public Page<ServiceProviderDTO> getListOfServiceProvider(int page) {
        Pageable pageable= PageRequest.of(page,20);
        Page<ServiceProviderEntity> serviceProviderEntities= serviceProviderRepository.findAll(pageable);
        return serviceProviderEntities.map(serviceProviderEntity -> toDTO(serviceProviderEntity));
    }

    public ServiceProviderDTO getServiceProviderById(Long serviceProviderId) {
        return toDTO(serviceProviderRepository.getOne(serviceProviderId));
    }

    public String deleteServiceProviderById(Long serviceProviderId) {
        serviceProviderRepository.deleteById(serviceProviderId);
        return ResponseValue.DELETE_SUCCESS;
    }
}
