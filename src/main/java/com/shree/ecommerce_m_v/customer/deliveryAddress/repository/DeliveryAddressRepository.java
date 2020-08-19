package com.shree.ecommerce_m_v.customer.deliveryAddress.repository;

import com.shree.ecommerce_m_v.customer.deliveryAddress.model.entity.DeliveryAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddressEntity,Long> {
}
