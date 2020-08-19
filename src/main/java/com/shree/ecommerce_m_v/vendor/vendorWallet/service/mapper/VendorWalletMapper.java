package com.shree.ecommerce_m_v.vendor.vendorWallet.service.mapper;

import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.vendor.vendorWallet.service.dto.VendorWalletDTO;
import com.shree.ecommerce_m_v.vendor.vendorWallet.model.entity.VendorWalletEntity;

public abstract class VendorWalletMapper {

    protected VendorWalletEntity toEntity(VendorWalletDTO vendorWalletDTO){
        VendorEntity vendorEntity= new VendorEntity();
        if(vendorWalletDTO.getVendorMergerDTO()!=null){
            vendorEntity=VendorEntity.builder()
                    .vendorId(vendorWalletDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(vendorWalletDTO.getVendorMergerDTO().getVendorName())
                    .build();
        }
        return VendorWalletEntity.builder()
                .currentAmount(vendorWalletDTO.getCurrentAmount())
                .vendorEntity(vendorWalletDTO.getVendorMergerDTO()!=null? vendorEntity: null)
                .build();

    }

    protected VendorWalletDTO toDTO(VendorWalletEntity vendorWalletEntity){
        VendorMergerDTO vendorMergerDTO= new VendorMergerDTO();
        if(vendorWalletEntity.getVendorEntity()!=null){
            vendorMergerDTO=VendorMergerDTO.builder()
                    .vendorId(vendorWalletEntity.getVendorEntity().getVendorId())
                    .vendorName(vendorWalletEntity.getVendorEntity().getVendorName())
                    .build();
        }
        return VendorWalletDTO.builder()
                .currentAmount(vendorWalletEntity.getCurrentAmount())
                .vendorMergerDTO(vendorMergerDTO)
                .build();

    }
}
