package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.repository;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.entity.DeliveryBoyWalletHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryBoyWalletHistoryRepository extends JpaRepository<DeliveryBoyWalletHistoryEntity, Long> {
}
