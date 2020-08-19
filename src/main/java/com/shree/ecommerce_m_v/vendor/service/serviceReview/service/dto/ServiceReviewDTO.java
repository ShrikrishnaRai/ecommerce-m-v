package com.shree.ecommerce_m_v.vendor.service.serviceReview.service.dto;

import com.shree.ecommerce_m_v.vendor.service.service.service.dto.ServiceMergerDTO;
import lombok.*;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceReviewDTO {

    private long serviceReviewId;
    private int rating;
    private String review;
    private String reply;

    private ServiceMergerDTO serviceMergerDTO;

}
