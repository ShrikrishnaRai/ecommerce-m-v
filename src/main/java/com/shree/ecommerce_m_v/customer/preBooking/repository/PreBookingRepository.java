package com.shree.ecommerce_m_v.customer.preBooking.repository;

import com.shree.ecommerce_m_v.customer.preBooking.model.entity.PreBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PreBookingRepository  extends JpaRepository<PreBookingEntity,Long> {
}
