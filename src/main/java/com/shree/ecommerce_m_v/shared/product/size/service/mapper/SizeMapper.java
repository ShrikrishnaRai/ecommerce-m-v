package com.shree.ecommerce_m_v.shared.product.size.service.mapper;

import com.shree.ecommerce_m_v.shared.product.size.service.dto.SizeMergerDTO;
import com.shree.ecommerce_m_v.shared.product.size.model.entity.SizeEntity;

public abstract class SizeMapper {

    protected SizeEntity toEntity(SizeMergerDTO sizeMergerDTO){
        return SizeEntity.builder()
                .size(sizeMergerDTO.getSize())
                .build();

    }

    protected SizeMergerDTO toDTO(SizeEntity sizeEntity){
        return SizeMergerDTO.builder()
                .sizeId(sizeEntity.getSizeId())
                .size(sizeEntity.getSize())
                .build();
    }
}
