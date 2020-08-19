package com.shree.ecommerce_m_v.vendor.coupon.service.dto;

import com.shree.ecommerce_m_v.vendor.coupon.model.DiscountType;
import com.shree.ecommerce_m_v.vendor.coupon.model.Status;
import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {

    private Long couponId;
    private String couponCode;
    private String name;
    private String description;
    private DiscountType discountType;
    private double discountValue;
    private LocalDate validFrom;
    private LocalDate validTo;
    private int usesPerCoupon;
    private Status status;

    private VendorMergerDTO vendorMergerDTO;
}
