package com.shree.ecommerce_m_v.vendor.vendorWallet.service.dto;

import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorWalletDTO {

    private Long vendorWalletId;
    private double currentAmount;

    private VendorMergerDTO vendorMergerDTO;
}
