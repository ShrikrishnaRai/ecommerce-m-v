package com.shree.ecommerce_m_v.shared.product.productImage.service.dto;

import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {

    private Long productImageId;
    private String productImage;
    private ProductMergerDTO productMergerDTO;


}
