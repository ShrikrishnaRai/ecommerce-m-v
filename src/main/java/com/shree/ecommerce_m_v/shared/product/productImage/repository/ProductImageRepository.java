package com.shree.ecommerce_m_v.shared.product.productImage.repository;

import com.shree.ecommerce_m_v.shared.product.productImage.model.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductImageRepository extends JpaRepository<ProductImageEntity,Long> {
}
