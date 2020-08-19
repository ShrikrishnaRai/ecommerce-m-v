package com.shree.ecommerce_m_v.customer.enquiry.service;

import com.shree.ecommerce_m_v.customer.enquiry.model.entity.EnquiryEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface EnquiryService {
    Page<EnquiryEntity> getAllEnquires(int page);

    Optional<EnquiryEntity> findEnquiryById(Long id);

    String deleteEnquiryById(Long id);

    Optional<EnquiryEntity> findEnquiryBySenderEmail(String email);

    EnquiryEntity saveEnquiry(EnquiryEntity enquiryEntity);

}
