package com.shree.ecommerce_m_v.vendor.coupon.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponMergerDTO {

    private Long couponId;
    private String couponCode;
}
