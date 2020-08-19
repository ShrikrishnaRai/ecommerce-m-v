package com.shree.ecommerce_m_v.vendor.service.serviceProvider.repository;

import com.shree.ecommerce_m_v.vendor.service.serviceProvider.model.entity.ServiceProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ServiceProviderRepository extends JpaRepository<ServiceProviderEntity,Long> {
}
