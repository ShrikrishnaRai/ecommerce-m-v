package com.shree.ecommerce_m_v.vendor.vendorReview.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorReviewMergerDTO {

    private Long vendorReviewId;
    private int rating;
}
