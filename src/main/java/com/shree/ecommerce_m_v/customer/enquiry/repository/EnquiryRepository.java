package com.shree.ecommerce_m_v.customer.enquiry.repository;

import com.shree.ecommerce_m_v.customer.enquiry.model.entity.EnquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface EnquiryRepository extends JpaRepository<EnquiryEntity, Long> {
    Optional<EnquiryEntity> findBySenderEmail(String email);
}
