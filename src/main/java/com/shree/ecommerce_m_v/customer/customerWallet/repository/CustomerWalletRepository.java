package com.shree.ecommerce_m_v.customer.customerWallet.repository;

import com.shree.ecommerce_m_v.customer.customerWallet.model.entity.CustomerWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerWalletRepository extends JpaRepository<CustomerWalletEntity, Long> {
}
