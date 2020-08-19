package com.shree.ecommerce_m_v.shared.product.productStock.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockMergerDTO {

    private Long productStockId;
    private int totalQuantity;
    private int remainingQuantity;
}
