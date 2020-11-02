package com.shree.ecommerce_m_v.vendor.service.serviceCategory.service;

import com.shree.ecommerce_m_v.vendor.service.serviceCategory.service.dto.ServiceCategoryDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceCategory.model.entity.ServiceCategoryEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceCategory.repository.ServiceCategoryRepository;
import com.shree.ecommerce_m_v.vendor.service.serviceCategory.service.mapper.ServiceCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ServiceCategoryService extends ServiceCategoryMapper  {

    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;


    public Page<ServiceCategoryDTO> getListOfServiceCategory(int page, String sortBy, String orderBy) {
        Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.valueOf(orderBy),sortBy));
        Page<ServiceCategoryEntity> serviceCategories = serviceCategoryRepository.findAll(pageable);
        return serviceCategories.map(this::toDTO);
    }

    public ServiceCategoryDTO getServiceCategoryById(long id) {
        return toDTO(serviceCategoryRepository.getOne(id));
    }

    public String saveServiceCategory(ServiceCategoryDTO serviceCategoryDTO) {
        serviceCategoryRepository.save(toEntity(serviceCategoryDTO));
        return "Service Category successfully added";
    }

    public ServiceCategoryDTO updateServiceCategory(long id, ServiceCategoryDTO serviceCategoryDTO) {

        ServiceCategoryEntity serviceCategoryEntity = serviceCategoryRepository.getOne(id);
        serviceCategoryEntity.setServiceCategoryId(serviceCategoryDTO.getServiceCategoryId());
        serviceCategoryEntity.setServiceCategoryName(serviceCategoryDTO.getServiceCategoryName());
        serviceCategoryEntity.setServiceDescription(serviceCategoryDTO.getServiceDescription());
        serviceCategoryEntity.setServiceSlug(serviceCategoryDTO.getServiceSlug());
        serviceCategoryRepository.save(serviceCategoryEntity);
        return toDTO(serviceCategoryEntity);
    }

    public String deleteServiceCategory(long id) {
        serviceCategoryRepository.deleteById(id);
        return "Service category successfully deleted";
    }

    public Page<ServiceCategoryDTO> getServiceCategoryByName(String serviceCategoryName) {
        Pageable pageable = PageRequest.of(0,10);
        Page<ServiceCategoryEntity> serviceCategoryEntities = serviceCategoryRepository.getServiceCategoryByName(serviceCategoryName,pageable);
        return serviceCategoryEntities.map(serviceCategoryEntity -> toDTO(serviceCategoryEntity));
    }


}
