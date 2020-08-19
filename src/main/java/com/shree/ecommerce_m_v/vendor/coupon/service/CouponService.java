package com.shree.ecommerce_m_v.vendor.coupon.service;

import com.shree.ecommerce_m_v.vendor.coupon.service.dto.CouponDTO;
import org.springframework.data.domain.Page;

public interface CouponService {

    String saveCoupon(CouponDTO couponDTO);

    String deleteCoupon(Long couponId);

    Page<CouponDTO> getCoupons(int page, String sortBy, String orderBy);

    CouponDTO updateCoupon(Long couponId,CouponDTO couponDTO);

    CouponDTO getCouponById(final Long couponId);
}
