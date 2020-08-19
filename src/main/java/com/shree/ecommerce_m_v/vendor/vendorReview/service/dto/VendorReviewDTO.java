package com.shree.ecommerce_m_v.vendor.vendorReview.service.dto;

import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import lombok.*;

@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorReviewDTO {

    private Long vendorReviewId;
    private int rating;
    private String review;
    private String reply;

    private VendorMergerDTO vendorMergerDTO;

}
