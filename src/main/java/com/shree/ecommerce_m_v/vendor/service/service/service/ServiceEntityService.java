package com.shree.ecommerce_m_v.vendor.service.service.service;

import com.shree.ecommerce_m_v.vendor.service.service.service.dto.ServiceDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ServiceEntityService {

    Page<ServiceDTO> getListOfService(int page, String sortBy, String orderBy);

    ServiceDTO getServiceById(long id);

    String saveService(ServiceDTO serviceDTO);

    String deleteService(long id);

    List<ServiceDTO> getServiceByName(String serviceName);

    ServiceDTO updateService(final Long serviceId,ServiceDTO serviceDTO);
}
