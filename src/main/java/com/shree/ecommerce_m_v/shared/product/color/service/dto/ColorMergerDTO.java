package com.shree.ecommerce_m_v.shared.product.color.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColorMergerDTO {
    private Long colorId;
    private String colorName;
    private String colorCode;


}
