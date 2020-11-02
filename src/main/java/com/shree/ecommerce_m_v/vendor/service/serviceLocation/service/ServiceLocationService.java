package com.shree.ecommerce_m_v.vendor.service.serviceLocation.service;

import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.dto.ServiceLocationDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceLocation.repository.ServiceLocationRepository;
import com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.mapper.ServiceLocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLocationService extends ServiceLocationMapper {

    @Autowired
    private ServiceLocationRepository serviceLocationRepository;


    public String saveServiceLocation(ServiceLocationDTO serviceLocationDTO) {
        serviceLocationRepository.saveAndFlush(toEntity(serviceLocationDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    public String deleteServiceLocationById(Long serviceLocationById) {
        serviceLocationRepository.deleteById(serviceLocationById);
        return ResponseValue.DELETE_SUCCESS;
    }

    public List<ServiceLocationDTO> getListOfServiceLocationByServiceProviderById(Long serviceProviderId) {
        return toDTOs(serviceLocationRepository.getServiceLocationByServiceProviderId(serviceProviderId));
    }

    public List<ServiceLocationDTO> getListOfServiceLocation() {
        return toDTOs(serviceLocationRepository.findAll());
    }
}
