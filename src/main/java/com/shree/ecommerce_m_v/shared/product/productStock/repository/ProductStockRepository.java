package com.shree.ecommerce_m_v.shared.product.productStock.repository;

import com.shree.ecommerce_m_v.shared.product.productStock.model.entity.ProductStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductStockRepository extends JpaRepository<ProductStockEntity, Long> {
}
