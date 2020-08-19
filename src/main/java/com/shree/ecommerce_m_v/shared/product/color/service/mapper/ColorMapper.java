package com.shree.ecommerce_m_v.shared.product.color.service.mapper;

import com.shree.ecommerce_m_v.shared.product.color.service.dto.ColorDTO;
import com.shree.ecommerce_m_v.shared.product.color.model.entity.ColorEntity;

public abstract class ColorMapper {

    protected ColorEntity toEntity(ColorDTO colorDTO){
        return ColorEntity.builder()
                .colorCode(colorDTO.getColorCode())
                .colorName(colorDTO.getColorName())
                .build();

    }

    protected ColorDTO toDTO(ColorEntity colorEntity){
        return ColorDTO.builder()
                .colorId(colorEntity.getColorId())
                .colorCode(colorEntity.getColorCode())
                .colorName(colorEntity.getColorName())
                .build();
    }
}
