package com.shree.ecommerce_m_v.shared.product.size.service;

import com.shree.ecommerce_m_v.shared.product.size.service.dto.SizeMergerDTO;
import com.shree.ecommerce_m_v.shared.product.size.model.entity.SizeEntity;
import com.shree.ecommerce_m_v.shared.product.size.repository.SizeRepository;
import com.shree.ecommerce_m_v.shared.product.size.service.mapper.SizeMapper;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SizeService extends SizeMapper {

    @Autowired
    private SizeRepository sizeRepository;

    public Page<SizeMergerDTO> getSize(int page) {

        Pageable pageable= PageRequest.of(page,20);
        Page<SizeEntity> sizes=sizeRepository.findAll(pageable);
        return sizes.map(sizeEntity -> toDTO(sizeEntity));
    }

    public SizeMergerDTO getSizeById(Long sizeId) {
        return toDTO( sizeRepository.getOne(sizeId));
    }

    public String saveSize(SizeMergerDTO sizeDTO) {
        sizeRepository.save(toEntity(sizeDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    public String deleteSize(Long sizeId) {
        sizeRepository.deleteById(sizeId);
        return ResponseValue.DELETE_SUCCESS;
    }

    public SizeMergerDTO updateSize(Long sizeId, SizeMergerDTO sizeDTO) {
        SizeEntity sizeEntity= sizeRepository.getOne(sizeId);
        sizeEntity.setSize(sizeDTO.getSize());
        sizeRepository.save(sizeEntity);
        return toDTO(sizeEntity);
    }
}
