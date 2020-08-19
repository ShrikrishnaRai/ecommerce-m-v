package com.shree.ecommerce_m_v.shared.product.color.service;

import com.shree.ecommerce_m_v.shared.product.color.service.dto.ColorDTO;
import com.shree.ecommerce_m_v.shared.product.color.model.entity.ColorEntity;
import com.shree.ecommerce_m_v.shared.product.color.repository.ColorRepository;
import com.shree.ecommerce_m_v.shared.product.color.service.mapper.ColorMapper;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImpl extends ColorMapper implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Page<ColorDTO> getColors(int page) {
        Pageable pageable= PageRequest.of(page,20);
        Page<ColorEntity> colors= colorRepository.findAll(pageable);
        return colors.map(colorEntity -> toDTO(colorEntity));
    }

    @Override
    public ColorDTO getColorById(Long colorId) {
        return toDTO(colorRepository.getOne(colorId));
    }

    @Override
    public String saveColors(ColorDTO colorDTO) {
        colorRepository.save(toEntity(colorDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public String deleteColors(Long colorId) {
        colorRepository.deleteById(colorId);
        return ResponseValue.DELETE_SUCCESS;
    }

    @Override
    public ColorDTO updateColor(Long colorId, ColorDTO colorDTO) {
        ColorEntity colorEntity=colorRepository.getOne(colorId);
        colorEntity.setColorCode(colorDTO.getColorCode());
        colorEntity.setColorName(colorDTO.getColorName());
        colorRepository.save(colorEntity);
        return toDTO(colorEntity);
    }
}
