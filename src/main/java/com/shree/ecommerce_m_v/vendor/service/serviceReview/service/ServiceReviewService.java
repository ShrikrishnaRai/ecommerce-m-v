package com.shree.ecommerce_m_v.vendor.service.serviceReview.service;

import com.shree.ecommerce_m_v.vendor.service.serviceReview.service.dto.ServiceReviewDTO;

import java.util.List;

public interface ServiceReviewService {

    List<ServiceReviewDTO> getListOfServiceReview();

    List<ServiceReviewDTO> getServiceReviewOfServiceWithServiceId(Long serviceId);

    String saveServiceReview(ServiceReviewDTO serviceReviewDTO);

    String deleteServiceReview(long id);

    ServiceReviewDTO updateServiceReview(long id, ServiceReviewDTO serviceReviewDTO);

}
