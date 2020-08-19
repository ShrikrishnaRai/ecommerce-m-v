package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.repository;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.entity.DeliveryBoyDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DeliveryBoyDocumentRepository extends JpaRepository<DeliveryBoyDocumentEntity, Long> {


}
