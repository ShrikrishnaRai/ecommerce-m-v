package com.shree.ecommerce_m_v.customer.order.orderStatus.repository;

import com.shree.ecommerce_m_v.customer.order.orderStatus.model.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Long> {
}
