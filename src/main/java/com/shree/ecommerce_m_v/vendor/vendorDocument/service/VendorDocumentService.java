package com.shree.ecommerce_m_v.vendor.vendorDocument.service;

import com.shree.ecommerce_m_v.vendor.vendorDocument.service.dto.VendorDocumentDTO;
import org.springframework.data.domain.Page;

public interface VendorDocumentService {

    Page<VendorDocumentDTO> getListOfVendorDocuments(int page);

    VendorDocumentDTO getVendorDocumentById(Long vendorDocumentId);

    String saveVendorDocument(VendorDocumentDTO vendorDocumentDTO);

    VendorDocumentDTO updateVendorDocumentDTO(Long vendorDocumentId,VendorDocumentDTO vendorDocumentDTO);


}
