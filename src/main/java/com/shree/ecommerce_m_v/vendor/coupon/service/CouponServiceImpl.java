package com.shree.ecommerce_m_v.vendor.coupon.service;

import com.shree.ecommerce_m_v.vendor.coupon.service.dto.CouponDTO;
import com.shree.ecommerce_m_v.vendor.coupon.model.entity.CouponEntity;
import com.shree.ecommerce_m_v.vendor.coupon.repository.CouponRepository;
import com.shree.ecommerce_m_v.vendor.coupon.service.mapper.CouponMapper;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class CouponServiceImpl extends CouponMapper implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public String saveCoupon(CouponDTO couponDTO) {

        couponRepository.save(toEntity(couponDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public String deleteCoupon(Long couponId) {
        couponRepository.deleteById(couponId);
        return ResponseValue.DELETE_SUCCESS;
    }

    @Override
    public Page<CouponDTO> getCoupons(int page,String sortBy,String orderBy) {

        Pageable pageable= PageRequest.of(page,10, Sort.by(Sort.Direction.valueOf(orderBy),sortBy));
        Page<CouponEntity> couponEntityPage=couponRepository.findAll(pageable);
        return couponEntityPage.map(couponEntity -> toDTO(couponEntity));
    }

    @Override
    public CouponDTO updateCoupon(Long couponId, CouponDTO couponDTO) {
        CouponEntity couponEntity=couponRepository.getOne(couponId);
        if(couponDTO.getVendorMergerDTO() !=null){
           couponEntity.setVendorEntity(VendorEntity.builder()
                    .vendorId(couponDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(couponDTO.getVendorMergerDTO().getVendorName())
                    .build());
        }

        couponEntity.setCouponCode(couponDTO.getCouponCode());
        couponEntity.setName(couponDTO.getName());
        couponEntity.setDescription(couponEntity.getDescription());
        couponEntity.setDiscountValue(couponDTO.getDiscountValue());
        couponEntity.setDiscountType(couponDTO.getDiscountType());
        couponEntity.setValidFrom(couponDTO.getValidFrom());
        couponEntity.setValidTo(couponDTO.getValidTo());
        couponEntity.setUsesPerCoupon(couponDTO.getUsesPerCoupon());
        couponEntity.setStatus(couponDTO.getStatus());
        couponRepository.save(couponEntity);
        return toDTO(couponEntity);
    }

    @Override
    public CouponDTO getCouponById(Long couponId) {
        return toDTO(couponRepository.getOne(couponId));
    }
}
