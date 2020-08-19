package com.shree.ecommerce_m_v.vendor.coupon.service.mapper;

import com.shree.ecommerce_m_v.vendor.coupon.service.dto.CouponDTO;
import com.shree.ecommerce_m_v.vendor.coupon.model.entity.CouponEntity;
import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;

public abstract class CouponMapper {

    protected CouponEntity toEntity(CouponDTO couponDTO){

        VendorEntity vendorEntity= new VendorEntity();
        if(couponDTO.getVendorMergerDTO() !=null){
            vendorEntity= VendorEntity.builder()
                    .vendorId(couponDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(couponDTO.getVendorMergerDTO().getVendorName())
                    .build();
        }
        return CouponEntity.builder()
                .couponCode(couponDTO.getCouponCode())
                .name(couponDTO.getName())
                .description(couponDTO.getDescription())
                .discountType(couponDTO.getDiscountType())
                .discountValue(couponDTO.getDiscountValue())
                .validFrom(couponDTO.getValidFrom())
                .validTo(couponDTO.getValidTo())
                .usesPerCoupon(couponDTO.getUsesPerCoupon())
                .status(couponDTO.getStatus())
                .vendorEntity(couponDTO.getVendorMergerDTO()!=null?vendorEntity:null)
                .build();
    }

    protected CouponDTO toDTO(CouponEntity couponEntity){

        VendorMergerDTO vendorMergerDTO= new VendorMergerDTO();
        if(couponEntity.getVendorEntity() !=null){
            vendorMergerDTO= VendorMergerDTO.builder()
                    .vendorId(couponEntity.getVendorEntity().getVendorId())
                    .vendorName(couponEntity.getVendorEntity().getVendorName())
                    .build();
        }
        return CouponDTO.builder()
                .couponId(couponEntity.getCouponId())
                .couponCode(couponEntity.getCouponCode())
                .name(couponEntity.getName())
                .description(couponEntity.getDescription())
                .discountType(couponEntity.getDiscountType())
                .discountValue(couponEntity.getDiscountValue())
                .validFrom(couponEntity.getValidFrom())
                .validTo(couponEntity.getValidTo())
                .usesPerCoupon(couponEntity.getUsesPerCoupon())
                .status(couponEntity.getStatus())
                .vendorMergerDTO(vendorMergerDTO)
                .build();
    }
}
