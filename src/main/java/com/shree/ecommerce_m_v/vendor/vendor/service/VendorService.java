package com.shree.ecommerce_m_v.vendor.vendor.service;

import com.shree.ecommerce_m_v.utils.email.otp.OtpConfig;
import com.shree.ecommerce_m_v.utils.email.otp.response.ResponseVal;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import com.shree.ecommerce_m_v.vendor.commission.model.entity.CommissionTypeEntity;
import com.shree.ecommerce_m_v.vendor.coupon.service.dto.CouponMergerDTO;
import com.shree.ecommerce_m_v.vendor.coupon.repository.CouponRepository;
import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorDTO;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.vendor.vendor.repository.VendorRepository;
import com.shree.ecommerce_m_v.vendor.vendor.service.mapper.VendorMapper;
import com.shree.ecommerce_m_v.vendor.vendorWallet.model.entity.VendorWalletEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VendorService extends VendorMapper  {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private OtpConfig otpConfig;


    public Page<VendorDTO> getVendorList(int page, String sortBy, String orderBy) {
        Pageable pageable = PageRequest.of(page, 15, Sort.by(Sort.Direction.valueOf(orderBy), sortBy));
        Page<VendorEntity> vendors = vendorRepository.findAll(pageable);
        return vendors.map(vendorEntity -> toDTO(vendorEntity));
    }


    public String saveVendor(VendorDTO vendorDTO) {
        VendorEntity vendorEntity = toEntity(vendorDTO);
        for (CouponMergerDTO couponMergerDTO : vendorDTO.getCouponMergerDTOS()) {
            if (couponRepository.existsById(couponMergerDTO.getCouponId())) {
                vendorEntity.addVendorToCoupon(couponRepository.getOne(couponMergerDTO.getCouponId()));
            }
        }
        vendorRepository.saveAndFlush(vendorEntity);
        return ResponseValue.SAVE_SUCCESS;
    }


    public VendorEntity getVendorEntity(VendorDTO vendorDTO) {
        return toEntity(vendorDTO);
    }


    public String findByEmail(String email, String contact, String username) {
        return vendorRepository.findByEmail(email, contact, username);
    }


    public String deleteVendorWithId(Long id) {
        VendorEntity vendorEntity = vendorRepository.getOne(id);
        if (vendorEntity.getCouponEntityList().size() != 0) {
            for (int i = vendorEntity.getCouponEntityList().size() - 1; i >= 0; --i) {
                vendorEntity.removeVendorFromCoupon(vendorEntity.getCouponEntityList().get(i));
            }
        }
        vendorRepository.deleteById(id);
        return ResponseValue.DELETE_SUCCESS;
    }


    public VendorDTO updateVendor(Long vendorId, VendorDTO vendorDTO) {
        VendorEntity vendorEntity = vendorRepository.getOne(vendorId);
        if (vendorDTO.getCouponMergerDTOS().size() != 0) {
            for (CouponMergerDTO couponMergerDTO : vendorDTO.getCouponMergerDTOS()) {
                if (couponRepository.existsById(couponMergerDTO.getCouponId())) {
                    vendorEntity.addVendorToCoupon(couponRepository.getOne(couponMergerDTO.getCouponId()));
                }
            }
        }

        if (vendorDTO.getCommissionMergerDTO() != null) {
            vendorEntity.setCommissionType(CommissionTypeEntity.builder()
                    .commissionTypeId(vendorDTO.getCommissionMergerDTO().getCommissionTypeId())
                    .commissionDescription(vendorDTO.getCommissionMergerDTO().getCommissionDescription())
                    .build());
        }

        if (vendorDTO.getVendorWalletMergerDTO() != null) {
            vendorEntity.setVendorWalletEntity(VendorWalletEntity.builder()
                    .vendorWalletId(vendorDTO.getVendorWalletMergerDTO().getVendorWalletId())
                    .currentAmount(vendorDTO.getVendorWalletMergerDTO().getCurrentAmount())
                    .build());
        }

        vendorEntity.setVendorName(vendorDTO.getVendorName());
        vendorEntity.setAddress(vendorDTO.getAddress());
        vendorEntity.setContact(vendorDTO.getContact());
        vendorEntity.setUsername(vendorDTO.getUsername());
        vendorEntity.setPassword(vendorDTO.getPassword());
        vendorEntity.setVendorType(vendorDTO.getVendorType());
        vendorEntity.setToken(vendorDTO.getToken());
        vendorEntity.setStatus(vendorDTO.getStatus());
        vendorEntity.setPanNo(vendorDTO.getPanNo());
        vendorEntity.setTaxClearanceNo(vendorDTO.getTaxClearanceNo());
        vendorEntity.setBusinessType(vendorDTO.getBusinessType());
        vendorEntity.setLatitude(vendorDTO.getLatitude());
        vendorEntity.setLongitude(vendorDTO.getLongitude());
        vendorEntity.setCommissionAmount(vendorDTO.getCommissionAmount());
        vendorEntity.setImage(vendorDTO.getImage());

        vendorRepository.saveAndFlush(vendorEntity);
        return toDTO(vendorEntity);

    }


    public VendorDTO getVendorWithId(Long id) {
        return toDTO(vendorRepository.getOne(id));
    }


    public Page<VendorDTO> getVendorWithName(String vendorName) {
        Pageable pageable = PageRequest.of(0, 15);
        Page<VendorEntity> vendors = vendorRepository.getVendorWithName(vendorName, pageable);
        return vendors.map(vendorEntity -> toDTO(vendorEntity));
    }


    public ResponseVal otpVerification(int otpNum, VendorEntity vendorEntity) {

        ResponseVal responseVal = new ResponseVal();
        if (otpNum > 0) {
            int serverOtp = otpConfig.generateOTP(vendorEntity.getContact().getEmail());
            if (otpNum == serverOtp) {
                otpConfig.clearOtp(vendorEntity.getContact().getEmail());
                vendorRepository.save(vendorEntity);

                responseVal.setCode(200);
                responseVal.setMessage("Successfully registered");
            } else {
                otpConfig.clearOtp(vendorEntity.getContact().getEmail());
                responseVal.setCode(200);
                responseVal.setMessage("Invalid otp");
            }
        }
        return responseVal;
    }


    public VendorDTO findVendorEntityByEmail(String email) {
        return toDTO(vendorRepository.findVendorEntityByEmail(email));
    }


}
