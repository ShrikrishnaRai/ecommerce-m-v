package com.shree.ecommerce_m_v.shared.product.productImage.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageMergerDTO {

    private Long productImageId;
    private String productImage;


}
