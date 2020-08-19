package com.shree.ecommerce_m_v.vendor.service.serviceReview.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceReviewMergerDTO {

    private Long serviceReviewId;
    private int rating;
}
