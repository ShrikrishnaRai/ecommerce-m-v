package com.shree.ecommerce_m_v.customer.order.order.repository;

import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query(value = "select * from order_table o where o.customer_id_fk=?1", nativeQuery = true)
    List<OrderEntity> getOrdersByCustomerId(Long customerId);
}
