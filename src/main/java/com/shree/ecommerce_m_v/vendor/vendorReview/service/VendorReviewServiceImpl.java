package com.shree.ecommerce_m_v.vendor.vendorReview.service;

import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.vendor.vendorReview.service.dto.VendorReviewDTO;
import com.shree.ecommerce_m_v.vendor.vendorReview.model.entity.VendorReviewEntity;
import com.shree.ecommerce_m_v.vendor.vendorReview.repository.VendorReviewRepository;
import com.shree.ecommerce_m_v.vendor.vendorReview.service.mapper.VendorReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorReviewServiceImpl extends VendorReviewMapper implements VendorReviewService {

    @Autowired
    private VendorReviewRepository vendorReviewRepository;


    @Override
    public String saveVendorReview(VendorReviewDTO vendorReviewDTO) {
        vendorReviewRepository.save(toEntity(vendorReviewDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public List<VendorReviewDTO> getReviewOfVendorWithVendorId(Long vendorId) {
        List<VendorReviewEntity> reviews =vendorReviewRepository.getReviewOfVendorWithVendorId(vendorId);
        return reviews
                .stream()
                .map(vendorReviewEntity -> toDTO(vendorReviewEntity))
                .collect(Collectors.toList());
    }

    @Override
    public VendorReviewDTO updateVendorReview(Long vendorReviewId, VendorReviewDTO vendorReviewDTO) {
        VendorReviewEntity vendorReviewEntity = vendorReviewRepository.getOne(vendorReviewId);
        if(vendorReviewDTO.getVendorMergerDTO()!=null){
            vendorReviewEntity.setVendorEntity(VendorEntity.builder()
                    .vendorId(vendorReviewDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(vendorReviewDTO.getVendorMergerDTO().getVendorName())
                    .build());
        }

        vendorReviewEntity.setReply(vendorReviewDTO.getReply());
        vendorReviewEntity.setRating(vendorReviewDTO.getRating());
        vendorReviewEntity.setReview(vendorReviewDTO.getReview());
        vendorReviewRepository.saveAndFlush(vendorReviewEntity);

        return toDTO(vendorReviewEntity);
    }
}
