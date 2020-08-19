package com.shree.ecommerce_m_v.shared.product.offer.repository;

import com.shree.ecommerce_m_v.shared.product.offer.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
}
