package com.shree.ecommerce_m_v.vendor.service.serviceLocation.service;

import com.shree.ecommerce_m_v.vendor.service.serviceLocation.service.dto.ServiceLocationDTO;

import java.util.List;

public interface ServiceLocationService {

    String saveServiceLocation(ServiceLocationDTO serviceLocationDTO);

    String deleteServiceLocationById(final Long serviceLocationById);

    List<ServiceLocationDTO>  getListOfServiceLocationByServiceProviderById(final Long serviceProviderId);

    List<ServiceLocationDTO> getListOfServiceLocation();



}
