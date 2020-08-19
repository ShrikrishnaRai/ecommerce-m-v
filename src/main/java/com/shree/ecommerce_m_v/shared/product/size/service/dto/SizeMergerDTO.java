package com.shree.ecommerce_m_v.shared.product.size.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeMergerDTO {

    private Long sizeId;
    private String size;


}
