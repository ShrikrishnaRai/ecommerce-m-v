package com.shree.ecommerce_m_v.vendor.vendor.service.mapper;

import com.shree.ecommerce_m_v.vendor.commission.model.dto.CommissionMergerDTO;
import com.shree.ecommerce_m_v.vendor.commission.model.entity.CommissionTypeEntity;
import com.shree.ecommerce_m_v.vendor.coupon.service.dto.CouponMergerDTO;
import com.shree.ecommerce_m_v.vendor.coupon.model.entity.CouponEntity;
import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorDTO;
import com.shree.ecommerce_m_v.vendor.vendor.model.Address;
import com.shree.ecommerce_m_v.vendor.vendor.model.Contact;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.vendor.vendorDocument.service.dto.VendorDocumentMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendorDocument.model.entity.VendorDocumentEntity;
import com.shree.ecommerce_m_v.vendor.vendorReview.service.dto.VendorReviewMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendorReview.model.entity.VendorReviewEntity;
import com.shree.ecommerce_m_v.vendor.vendorWallet.service.dto.VendorWalletMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendorWallet.model.entity.VendorWalletEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class VendorMapper {

    private List<CouponEntity> couponEntities = new ArrayList<>();

    public List<CouponEntity> getCouponEntities() {
        return this.couponEntities;
    }

    protected VendorDTO toDTO(VendorEntity vendorEntity) {

        List<CouponMergerDTO> couponMergerDTOS = new ArrayList<>();
        if (vendorEntity.getCouponEntityList().size() != 0) {
            for (CouponEntity couponEntity : vendorEntity.getCouponEntityList()) {
                couponMergerDTOS.add(CouponMergerDTO.builder()
                        .couponId(couponEntity.getCouponId())
                        .couponCode(couponEntity.getCouponCode())
                        .build());
            }
        }

        List<VendorReviewMergerDTO> vendorReviewMergerDTOS = new ArrayList<>();
        if (vendorEntity.getVendorReviewEntities().size() != 0) {
            for (VendorReviewEntity vendorReviewEntity : vendorEntity.getVendorReviewEntities()) {
                vendorReviewMergerDTOS.add(VendorReviewMergerDTO.builder()
                        .vendorReviewId(vendorReviewEntity.getVendorReviewId())
                        .rating(vendorReviewEntity.getRating())
                        .build());
            }
        }

        CommissionMergerDTO commissionMergerDTO = new CommissionMergerDTO();
        if (vendorEntity.getCommissionType() != null) {
            commissionMergerDTO = CommissionMergerDTO.builder()
                    .commissionTypeId(vendorEntity.getCommissionType().getCommissionTypeId())
                    .commissionDescription(vendorEntity.getCommissionType().getCommissionDescription())
                    .build();
        }
        VendorWalletMergerDTO vendorWalletMergerDTO = new VendorWalletMergerDTO();
        if (vendorEntity.getVendorWalletEntity() != null) {
            vendorWalletMergerDTO = VendorWalletMergerDTO.builder()
                    .vendorWalletId(vendorEntity.getVendorWalletEntity().getVendorWalletId())
                    .currentAmount(vendorEntity.getVendorWalletEntity().getCurrentAmount())
                    .build();
        }

        VendorDocumentMergerDTO vendorDocumentMergerDTO = new VendorDocumentMergerDTO();
        if (vendorEntity.getVendorDocumentEntity() != null) {
            vendorDocumentMergerDTO = VendorDocumentMergerDTO.builder()
                    .vendorDocumentId(vendorEntity.getVendorDocumentEntity().getVendorDocumentId())
                    .panDocument(vendorEntity.getVendorDocumentEntity().getPanDocument())
                    .build();
        }


        return VendorDTO.builder()
                .id(vendorEntity.getVendorId())
                .vendorName(vendorEntity.getVendorName())
                .address(vendorEntity.getAddress() != null ? Address.builder()
                        .area(vendorEntity.getAddress().getArea())
                        .state(vendorEntity.getAddress().getState())
                        .street(vendorEntity.getAddress().getStreet())
                        .district(vendorEntity.getAddress().getDistrict())
                        .country(vendorEntity.getAddress().getCountry())
                        .zipCode(vendorEntity.getAddress().getZipCode())
                        .build() : null)
                .contact(vendorEntity.getContact() != null ? Contact.builder()
                        .contactNo(vendorEntity.getContact().getContactNo())
                        .email(vendorEntity.getContact().getEmail())
                        .build() : null)
                .username(vendorEntity.getUsername())
                .password(vendorEntity.getPassword())
                .vendorType(vendorEntity.getVendorType())
                .token(vendorEntity.getToken())
                .status(vendorEntity.getStatus())
                .panNo(vendorEntity.getPanNo())
                .taxClearanceNo(vendorEntity.getTaxClearanceNo())
                .businessType(vendorEntity.getBusinessType())
                .latitude(vendorEntity.getLatitude())
                .longitude(vendorEntity.getLongitude())
                .commissionAmount(vendorEntity.getCommissionAmount())
                .image(vendorEntity.getImage())
                .commissionMergerDTO(commissionMergerDTO)
                .couponMergerDTOS(couponMergerDTOS)
                .vendorWalletMergerDTO(vendorWalletMergerDTO)
                .vendorReviewMergerDTOS(vendorReviewMergerDTOS)
                .vendorDocumentMergerDTO(vendorDocumentMergerDTO)
                .build();
    }

    protected VendorEntity toEntity(VendorDTO vendorDTO) {

        if (vendorDTO.getCouponMergerDTOS().size() != 0) {
            for (CouponMergerDTO couponMergerDTO : vendorDTO.getCouponMergerDTOS()) {
                couponEntities.add(CouponEntity.builder()
                        .couponId(couponMergerDTO.getCouponId())
                        .couponCode(couponMergerDTO.getCouponCode())
                        .build());
            }
        }

        CommissionTypeEntity commissionTypeEntity = new CommissionTypeEntity();
        if (vendorDTO.getCommissionMergerDTO() != null) {
            commissionTypeEntity = CommissionTypeEntity.builder()
                    .commissionTypeId(vendorDTO.getCommissionMergerDTO().getCommissionTypeId())
                    .commissionDescription(vendorDTO.getCommissionMergerDTO().getCommissionDescription())
                    .build();
        }

        VendorWalletEntity vendorWalletEntity = new VendorWalletEntity();
        if (vendorDTO.getVendorWalletMergerDTO() != null) {
            vendorWalletEntity = VendorWalletEntity.builder()
                    .vendorWalletId(vendorDTO.getVendorWalletMergerDTO().getVendorWalletId())
                    .currentAmount(vendorDTO.getVendorWalletMergerDTO().getCurrentAmount())
                    .build();
        }

        VendorDocumentEntity vendorDocumentEntity = new VendorDocumentEntity();
        if (vendorDTO.getVendorDocumentMergerDTO() != null) {
            vendorDocumentEntity = VendorDocumentEntity.builder()
                    .vendorDocumentId(vendorDTO.getVendorDocumentMergerDTO().getVendorDocumentId())
                    .panDocument(vendorDTO.getVendorDocumentMergerDTO().getPanDocument())
                    .build();
        }

        return VendorEntity.builder()
                .vendorId(vendorDTO.getId())
                .vendorName(vendorDTO.getVendorName())
                .address(Address.builder()
                        .area(vendorDTO.getAddress().getArea())
                        .state(vendorDTO.getAddress().getState())
                        .street(vendorDTO.getAddress().getStreet())
                        .district(vendorDTO.getAddress().getDistrict())
                        .country(vendorDTO.getAddress().getCountry())
                        .zipCode(vendorDTO.getAddress().getZipCode())
                        .build())
                .contact(Contact.builder()
                        .contactNo(vendorDTO.getContact().getContactNo())
                        .email(vendorDTO.getContact().getEmail())
                        .build())
                .username(vendorDTO.getUsername())
                .password(vendorDTO.getPassword())
                .vendorType(vendorDTO.getVendorType())
                .token(vendorDTO.getToken())
                .status(vendorDTO.getStatus())
                .panNo(vendorDTO.getPanNo())
                .taxClearanceNo(vendorDTO.getTaxClearanceNo())
                .businessType(vendorDTO.getBusinessType())
                .latitude(vendorDTO.getLatitude())
                .longitude(vendorDTO.getLongitude())
                .commissionAmount(vendorDTO.getCommissionAmount())
                .image(vendorDTO.getImage())
                .couponEntityList(vendorDTO.getCouponMergerDTOS().size() != 0 ? couponEntities : null)
                .commissionType(vendorDTO.getCommissionMergerDTO() != null ? commissionTypeEntity : null)
                .vendorWalletEntity(vendorDTO.getVendorWalletMergerDTO() != null ? vendorWalletEntity : null)
                .vendorDocumentEntity(vendorDTO.getVendorDocumentMergerDTO() != null ? vendorDocumentEntity : null)
                .build();
    }

    protected List<VendorDTO> toVendorEntityList(List<VendorEntity> vendorEntityList) {
        return vendorEntityList
                .stream()
                .map((vendorEntity) -> toDTO(vendorEntity))
                .collect(Collectors.toList());
    }
}
