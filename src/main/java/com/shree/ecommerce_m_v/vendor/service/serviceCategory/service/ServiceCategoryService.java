package com.shree.ecommerce_m_v.vendor.service.serviceCategory.service;

import com.shree.ecommerce_m_v.vendor.service.serviceCategory.service.dto.ServiceCategoryDTO;
import org.springframework.data.domain.Page;

public interface ServiceCategoryService {

    Page<ServiceCategoryDTO> getListOfServiceCategory(int page, String sortBy, String orderBy);

    ServiceCategoryDTO getServiceCategoryById(long id);

    String saveServiceCategory(ServiceCategoryDTO serviceCategoryDTO);

    ServiceCategoryDTO updateServiceCategory(long id, ServiceCategoryDTO serviceCategoryDTO);

    String deleteServiceCategory(long id);

    Page<ServiceCategoryDTO> getServiceCategoryByName(String serviceCategoryName);

}
