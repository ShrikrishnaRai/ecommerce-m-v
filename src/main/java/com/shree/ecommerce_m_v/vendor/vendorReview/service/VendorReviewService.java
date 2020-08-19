package com.shree.ecommerce_m_v.vendor.vendorReview.service;

import com.shree.ecommerce_m_v.vendor.vendorReview.service.dto.VendorReviewDTO;

import java.util.List;

public interface VendorReviewService {


    String saveVendorReview(VendorReviewDTO vendorReviewDTO);

    List<VendorReviewDTO> getReviewOfVendorWithVendorId(Long vendorId);

    VendorReviewDTO updateVendorReview(Long vendorReviewId,VendorReviewDTO vendorReviewDTO);
}
