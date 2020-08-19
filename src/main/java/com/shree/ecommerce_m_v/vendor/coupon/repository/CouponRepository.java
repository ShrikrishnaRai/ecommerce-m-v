package com.shree.ecommerce_m_v.vendor.coupon.repository;

import com.shree.ecommerce_m_v.vendor.coupon.model.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CouponRepository extends JpaRepository<CouponEntity,Long> {
}
