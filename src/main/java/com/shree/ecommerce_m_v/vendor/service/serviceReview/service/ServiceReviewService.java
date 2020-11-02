package com.shree.ecommerce_m_v.vendor.service.serviceReview.service;

import com.shree.ecommerce_m_v.vendor.service.service.model.entity.ServiceEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceReview.service.dto.ServiceReviewDTO;
import com.shree.ecommerce_m_v.vendor.service.serviceReview.model.entity.ServiceReviewEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceReview.repository.ServiceReviewRepository;
import com.shree.ecommerce_m_v.vendor.service.serviceReview.service.mapper.ServiceReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceReviewService extends ServiceReviewMapper  {

    @Autowired
    private ServiceReviewRepository serviceReviewRepository;


    public List<ServiceReviewDTO> getListOfServiceReview() {
        return toDTOs(serviceReviewRepository.findAll());
    }


    public List<ServiceReviewDTO> getServiceReviewOfServiceWithServiceId(Long serviceId) {
        return toDTOs(serviceReviewRepository.getServiceReviewOfServiceWithServiceId(serviceId));
    }


    public String saveServiceReview(ServiceReviewDTO serviceReviewDTO) {
        serviceReviewRepository.save(toEntity(serviceReviewDTO));
        return "Service Review successfully added";
    }


    public String deleteServiceReview(long id) {
        serviceReviewRepository.deleteById(id);
        return "Service review deleted successfully";
    }


    public ServiceReviewDTO updateServiceReview(long id, ServiceReviewDTO serviceReviewDTO) {
        ServiceReviewEntity serviceReviewEntity = new ServiceReviewEntity();
        serviceReviewEntity.setServiceReviewId(serviceReviewDTO.getServiceReviewId());
        serviceReviewEntity.setRating(serviceReviewDTO.getRating());
        serviceReviewEntity.setReview(serviceReviewDTO.getReview());
        serviceReviewEntity.setReply(serviceReviewDTO.getReply());
        if (serviceReviewDTO.getServiceMergerDTO() != null) {
            serviceReviewEntity.setService(ServiceEntity.builder()
                    .serviceId(serviceReviewDTO.getServiceMergerDTO().getServiceId())
                    .serviceName(serviceReviewDTO.getServiceMergerDTO().getServiceName())
                    .build());
        }
        serviceReviewRepository.save(serviceReviewEntity);
        return toDTO(serviceReviewEntity);
    }


}
