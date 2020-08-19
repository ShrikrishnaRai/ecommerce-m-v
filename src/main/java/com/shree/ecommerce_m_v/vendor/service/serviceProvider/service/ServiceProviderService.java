package com.shree.ecommerce_m_v.vendor.service.serviceProvider.service;

import com.shree.ecommerce_m_v.vendor.service.serviceProvider.service.dto.ServiceProviderDTO;
import org.springframework.data.domain.Page;

public interface ServiceProviderService {

    String saveServiceProvider(ServiceProviderDTO serviceProviderDTO);

    ServiceProviderDTO updateServiceProvider(final Long serviceProviderId,ServiceProviderDTO serviceProviderDTO);

    Page<ServiceProviderDTO> getListOfServiceProvider(int page);

    ServiceProviderDTO getServiceProviderById(final Long serviceProviderId);

    String deleteServiceProviderById(final Long serviceProviderId);
}
