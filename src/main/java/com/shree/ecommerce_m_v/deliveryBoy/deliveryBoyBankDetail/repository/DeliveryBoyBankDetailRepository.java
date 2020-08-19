package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.repository;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.entity.DeliveryBoyBankDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryBoyBankDetailRepository extends JpaRepository<DeliveryBoyBankDetailEntity, Long> {


}
