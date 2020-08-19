package com.shree.ecommerce_m_v.shared.product.product.service.dto;

import com.shree.ecommerce_m_v.shared.product.color.service.dto.ColorMergerDTO;
import com.shree.ecommerce_m_v.shared.product.size.service.dto.SizeMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {

    private Long productDetailId;
    private ProductMergerDTO productEntity;
    private List<SizeMergerDTO> sizeEntity = new ArrayList<>();
    private ColorMergerDTO colorEntity;
    private int totalQuantity;
    private int remainingQuantity;
    private boolean isAvailable;

}
