package com.shree.ecommerce_m_v.shared.product.color.service.dto;

import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ColorDTO {

    private Long colorId;
    private String colorName;
    private String colorCode;

    private List<ProductMergerDTO> ProductMergerDTOS= new ArrayList<>();



}
