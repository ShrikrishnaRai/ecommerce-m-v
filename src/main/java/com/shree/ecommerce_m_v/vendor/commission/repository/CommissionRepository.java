package com.shree.ecommerce_m_v.vendor.commission.repository;

import com.shree.ecommerce_m_v.vendor.commission.model.entity.CommissionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<CommissionTypeEntity,Long> {
}
