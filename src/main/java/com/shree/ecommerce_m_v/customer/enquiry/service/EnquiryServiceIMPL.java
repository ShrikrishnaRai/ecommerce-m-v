package com.shree.ecommerce_m_v.customer.enquiry.service;

import com.shree.ecommerce_m_v.customer.enquiry.model.entity.EnquiryEntity;
import com.shree.ecommerce_m_v.customer.enquiry.repository.EnquiryRepository;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class EnquiryServiceIMPL implements EnquiryService {

    private final EnquiryRepository enquiryRepository;

    public EnquiryServiceIMPL(EnquiryRepository enquiryRepository) {
        this.enquiryRepository = enquiryRepository;
    }

    @Override
    public Page<EnquiryEntity> getAllEnquires(int page) {
        Pageable pageable = PageRequest.of(page,8);
        return enquiryRepository.findAll(pageable);
    }

    @Override
    public Optional<EnquiryEntity> findEnquiryById(Long id) {
        return Optional.of(enquiryRepository.findById(id)).orElseThrow(() ->
                new EntityNotFoundException("Enquiry with id " + id + "not found"));
    }

    @Override
    public String deleteEnquiryById(Long id) {
        if (findEnquiryById(id).isPresent()) {
            enquiryRepository.deleteById(id);
            return ResponseValue.DELETE_SUCCESS;
        } else {
            return ResponseValue.DELETE_UN_SUCCESS;
        }
    }

    @Override
    public Optional<EnquiryEntity> findEnquiryBySenderEmail(@RequestBody String email) {
        return Optional.of(enquiryRepository.findBySenderEmail(email)).orElseThrow(() ->
                new EntityNotFoundException("Enquiry with id " + email + "not found"));
    }

    @Override
    public EnquiryEntity saveEnquiry(EnquiryEntity enquiryEntity) {
        return enquiryRepository.save(enquiryEntity);
    }

}
