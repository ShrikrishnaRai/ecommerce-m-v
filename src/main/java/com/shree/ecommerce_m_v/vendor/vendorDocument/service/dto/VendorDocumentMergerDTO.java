package com.shree.ecommerce_m_v.vendor.vendorDocument.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorDocumentMergerDTO {

    private Long vendorDocumentId;
    private String panDocument;
}
