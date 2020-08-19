package com.shree.ecommerce_m_v.shared.product.size.repository;

import com.shree.ecommerce_m_v.shared.product.size.model.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SizeRepository extends JpaRepository<SizeEntity,Long> {
}
