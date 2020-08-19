package com.shree.ecommerce_m_v.shared.product.size.service;

import com.shree.ecommerce_m_v.shared.product.size.service.dto.SizeMergerDTO;
import org.springframework.data.domain.Page;

public interface SizeService {

    Page<SizeMergerDTO> getSize(int page);

    SizeMergerDTO getSizeById(final Long sizeId);

    String saveSize(SizeMergerDTO sizeDTO);

    String deleteSize(Long sizeId);

    SizeMergerDTO updateSize(Long sizeId,SizeMergerDTO sizeDTO);
}
