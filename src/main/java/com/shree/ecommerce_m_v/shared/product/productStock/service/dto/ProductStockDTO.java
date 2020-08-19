package com.shree.ecommerce_m_v.shared.product.productStock.service.dto;

import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductStockDTO {

    private Long productStockId;
    private int totalQuantity;
    private int remainingQuantity;
    private ProductMergerDTO productMergerDTO;

}
