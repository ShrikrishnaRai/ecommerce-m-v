package com.shree.ecommerce_m_v.vendor.vendorReview.service.mapper;

import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.vendor.vendorReview.service.dto.VendorReviewDTO;
import com.shree.ecommerce_m_v.vendor.vendorReview.model.entity.VendorReviewEntity;

public abstract class VendorReviewMapper {

    protected VendorReviewEntity toEntity(VendorReviewDTO vendorReviewDTO){
        VendorEntity vendorEntity = new VendorEntity();
        if(vendorReviewDTO.getVendorMergerDTO() != null){
             vendorEntity = VendorEntity.builder()
                     .vendorId(vendorReviewDTO.getVendorMergerDTO().getVendorId())
                     .vendorName(vendorReviewDTO.getVendorMergerDTO().getVendorName())
                     .build();
        }
        return VendorReviewEntity.builder()
                .rating(vendorReviewDTO.getRating())
                .review(vendorReviewDTO.getReview())
                .reply(vendorReviewDTO.getReply())
                .vendorEntity(vendorReviewDTO.getVendorMergerDTO()!=null? vendorEntity: null)
                .build();
    }

    protected VendorReviewDTO toDTO(VendorReviewEntity vendorReviewEntity){
        VendorMergerDTO vendorMergerDTO = new VendorMergerDTO();
        if(vendorReviewEntity.getVendorEntity() != null){
            vendorMergerDTO = VendorMergerDTO.builder()
                    .vendorId(vendorReviewEntity.getVendorEntity().getVendorId())
                    .vendorName(vendorReviewEntity.getVendorEntity().getVendorName())
                    .build();
        }
        return VendorReviewDTO.builder()
                .vendorReviewId(vendorReviewEntity.getVendorReviewId())
                .rating(vendorReviewEntity.getRating())
                .review(vendorReviewEntity.getReview())
                .reply(vendorReviewEntity.getReply())
                .vendorMergerDTO(vendorMergerDTO)
                .build();
    }
}
