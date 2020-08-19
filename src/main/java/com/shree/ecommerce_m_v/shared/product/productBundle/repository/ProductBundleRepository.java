package com.shree.ecommerce_m_v.shared.product.productBundle.repository;

import com.shree.ecommerce_m_v.shared.product.productBundle.model.entity.ProductBundleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductBundleRepository extends JpaRepository<ProductBundleEntity, Long> {
}
