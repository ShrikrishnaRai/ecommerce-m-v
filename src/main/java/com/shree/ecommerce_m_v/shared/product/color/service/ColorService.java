package com.shree.ecommerce_m_v.shared.product.color.service;

import com.shree.ecommerce_m_v.shared.product.color.service.dto.ColorDTO;
import org.springframework.data.domain.Page;

public interface ColorService {

    Page<ColorDTO> getColors(int page);

    ColorDTO getColorById(final Long colorId);

    String saveColors(ColorDTO colorDTO);

    String deleteColors(Long colorId);

    ColorDTO updateColor(Long colorId,ColorDTO colorDTO);
}
