package com.shree.ecommerce_m_v.customer.complain.repository;

import com.shree.ecommerce_m_v.customer.complain.model.entity.ComplainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainRepository extends JpaRepository<ComplainEntity, Long> {
}
