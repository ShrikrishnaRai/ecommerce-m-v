package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.repository;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.entity.DeliveryBoyPaymentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryBoyPaymentHistoryRepository extends JpaRepository<DeliveryBoyPaymentHistoryEntity, Long> {
}
