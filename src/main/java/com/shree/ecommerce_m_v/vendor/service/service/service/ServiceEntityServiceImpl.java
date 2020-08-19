package com.shree.ecommerce_m_v.vendor.service.service.service;

import com.shree.ecommerce_m_v.vendor.service.service.service.dto.ServiceDTO;
import com.shree.ecommerce_m_v.vendor.service.service.model.entity.ServiceEntity;
import com.shree.ecommerce_m_v.vendor.service.service.repository.ServiceRepository;
import com.shree.ecommerce_m_v.vendor.service.service.service.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEntityServiceImpl extends ServiceMapper implements ServiceEntityService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Page<ServiceDTO> getListOfService(int page, String sortBy, String orderBy) {
        Pageable pageable = PageRequest.of(page,8, Sort.by(Sort.Direction.valueOf(orderBy),sortBy));
        Page<ServiceEntity> result = serviceRepository.findAll(pageable);

        return result.map(serviceEntity -> toDTO(serviceEntity));
    }

    @Override
    public ServiceDTO getServiceById(long id) {
        return toDTO(serviceRepository.getOne(id));
    }

    @Override
    public String saveService(ServiceDTO serviceDTO) {
        serviceRepository.save(toEntity(serviceDTO));
        return "Service added successfully";
    }

    @Override
    public String deleteService(long id) {
        serviceRepository.deleteById(id);
        return null;
    }

    @Override
    public List<ServiceDTO> getServiceByName(String serviceName) {
        return toDTOs(serviceRepository.getServiceByName(serviceName));
    }

    @Override
    public ServiceDTO updateService(Long serviceId, ServiceDTO serviceDTO) {

        ServiceEntity serviceEntity= serviceRepository.getOne(serviceId);
        serviceEntity.setDescription(serviceDTO.getDescription());
        serviceEntity.setServiceName(serviceDTO.getServiceName());
        serviceEntity.setParentServiceId(serviceDTO.getParentServiceId());
        serviceRepository.save(serviceEntity);
        return toDTO(serviceEntity);
    }
}
