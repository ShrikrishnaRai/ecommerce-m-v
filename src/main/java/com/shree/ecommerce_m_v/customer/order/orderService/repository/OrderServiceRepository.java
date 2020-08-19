package com.shree.ecommerce_m_v.customer.order.orderService.repository;

import com.shree.ecommerce_m_v.customer.order.orderService.model.entity.OrderServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderServiceEntity, Long> {
}
