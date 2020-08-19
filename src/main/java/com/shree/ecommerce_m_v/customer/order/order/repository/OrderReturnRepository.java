package com.shree.ecommerce_m_v.customer.order.order.repository;

import com.shree.ecommerce_m_v.customer.order.order.model.entity.orderReturn.OrderReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface OrderReturnRepository extends JpaRepository<OrderReturn, Long> {
}
