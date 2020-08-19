package com.shree.ecommerce_m_v.vendor.vendorDocument.service.dto;

import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorDocumentDTO {

    private Long vendorDocumentId;
    private String panDocument;
    private String vatDocument;
    private String taxClearance;

    private VendorMergerDTO vendorMergerDTO;

}
