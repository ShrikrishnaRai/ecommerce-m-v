package com.shree.ecommerce_m_v.customer.order.invoice.repository;

import com.shree.ecommerce_m_v.customer.order.invoice.model.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
}
