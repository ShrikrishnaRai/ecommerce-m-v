package com.shree.ecommerce_m_v.customer.order.cancelOrder.repository;

import com.shree.ecommerce_m_v.customer.order.cancelOrder.model.entity.CancelOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelOrderRepository extends JpaRepository<CancelOrderEntity, Long> {
}
