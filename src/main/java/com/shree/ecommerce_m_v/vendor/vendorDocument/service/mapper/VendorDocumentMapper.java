package com.shree.ecommerce_m_v.vendor.vendorDocument.service.mapper;

import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.vendor.vendorDocument.service.dto.VendorDocumentDTO;
import com.shree.ecommerce_m_v.vendor.vendorDocument.model.entity.VendorDocumentEntity;

public abstract class VendorDocumentMapper {

    protected VendorDocumentEntity toEntity(VendorDocumentDTO vendorDocumentDTO){
        VendorEntity vendorEntity = new VendorEntity();
        if(vendorDocumentDTO.getVendorMergerDTO() != null){
            vendorEntity = VendorEntity.builder()
                    .vendorId(vendorDocumentDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(vendorDocumentDTO.getVendorMergerDTO().getVendorName())
                    .build();
        }

        return VendorDocumentEntity.builder()
                .panDocument(vendorDocumentDTO.getPanDocument())
                .vatDocument(vendorDocumentDTO.getVatDocument())
                .taxClearance(vendorDocumentDTO.getTaxClearance())
                .vendorEntity(vendorDocumentDTO.getVendorMergerDTO()!=null ? vendorEntity: null)
                .build();
    }

    protected VendorDocumentDTO toDTO(VendorDocumentEntity vendorDocumentEntity){
        VendorMergerDTO vendorMergerDTO = new VendorMergerDTO();
        if(vendorDocumentEntity.getVendorEntity() != null){
            vendorMergerDTO = VendorMergerDTO.builder()
                    .vendorId(vendorDocumentEntity.getVendorEntity().getVendorId())
                    .vendorName(vendorDocumentEntity.getVendorEntity().getVendorName())
                    .build();
        }

        return VendorDocumentDTO.builder()
                .vendorDocumentId(vendorDocumentEntity.getVendorDocumentId())
                .panDocument(vendorDocumentEntity.getPanDocument())
                .vatDocument(vendorDocumentEntity.getVatDocument())
                .taxClearance(vendorDocumentEntity.getTaxClearance())
                .vendorMergerDTO(vendorMergerDTO)
                .build();
    }
}
