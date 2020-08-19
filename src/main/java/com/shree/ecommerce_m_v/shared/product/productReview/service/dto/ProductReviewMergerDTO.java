package com.shree.ecommerce_m_v.shared.product.productReview.service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewMergerDTO {

    private Long productReviewId;
    private float rating;
}
