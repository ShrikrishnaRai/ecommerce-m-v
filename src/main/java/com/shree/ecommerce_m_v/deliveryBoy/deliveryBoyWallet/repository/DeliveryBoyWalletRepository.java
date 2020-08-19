package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.repository;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.entity.DeliveryBoyWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryBoyWalletRepository extends JpaRepository<DeliveryBoyWalletEntity, Long> {
}
