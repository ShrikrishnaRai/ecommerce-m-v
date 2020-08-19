package com.shree.ecommerce_m_v.vendor.vendorDocument.service;

import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.vendor.vendorDocument.service.dto.VendorDocumentDTO;
import com.shree.ecommerce_m_v.vendor.vendorDocument.model.entity.VendorDocumentEntity;
import com.shree.ecommerce_m_v.vendor.vendorDocument.repository.VendorDocumentRepository;
import com.shree.ecommerce_m_v.vendor.vendorDocument.service.mapper.VendorDocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VendorDocumentServiceImpl extends VendorDocumentMapper implements VendorDocumentService {


    @Autowired
    private VendorDocumentRepository vendorDocumentRepository;

    @Override
    public Page<VendorDocumentDTO> getListOfVendorDocuments(int page) {
        Pageable pageable= PageRequest.of(page,10);
        Page<VendorDocumentEntity> vendorDocuments = vendorDocumentRepository.findAll(pageable);

        return vendorDocuments.map(this::toDTO);
    }

    @Override
    public VendorDocumentDTO getVendorDocumentById(Long vendorDocumentId) {
        return toDTO(vendorDocumentRepository.getOne(vendorDocumentId));
    }

    @Override
    public String saveVendorDocument(VendorDocumentDTO vendorDocumentDTO) {
        vendorDocumentRepository.save(toEntity(vendorDocumentDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public VendorDocumentDTO updateVendorDocumentDTO(Long vendorDocumentId, VendorDocumentDTO vendorDocumentDTO) {
        VendorDocumentEntity vendorDocumentEntity = vendorDocumentRepository.getOne(vendorDocumentId);
        vendorDocumentEntity.setVendorDocumentId(vendorDocumentDTO.getVendorDocumentId());
        vendorDocumentEntity.setPanDocument(vendorDocumentDTO.getPanDocument());
        vendorDocumentEntity.setTaxClearance(vendorDocumentDTO.getTaxClearance());
        vendorDocumentEntity.setVatDocument(vendorDocumentDTO.getTaxClearance());
        if(vendorDocumentDTO.getVendorMergerDTO() != null){
            vendorDocumentEntity.setVendorEntity(VendorEntity.builder()
                    .vendorId(vendorDocumentDTO.getVendorMergerDTO().getVendorId())
                    .vendorName(vendorDocumentDTO.getVendorMergerDTO().getVendorName())
                    .build());
        }
        vendorDocumentRepository.save(vendorDocumentEntity);
        return toDTO(vendorDocumentEntity);
    }
}
