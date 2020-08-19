package com.shree.ecommerce_m_v.shared.product.product.model.DTO;

import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryMergerDTO;
import lombok.*;

/**
 * This class is merged with other class on rest response
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponseMergedDTO {
    private int productId;
    private CategoryMergerDTO categoryMergerDTO;
    private Double price;
    private String productURL;
    private String productName;
}
