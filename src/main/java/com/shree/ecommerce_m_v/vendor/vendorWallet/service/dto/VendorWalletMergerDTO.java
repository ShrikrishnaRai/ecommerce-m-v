package com.shree.ecommerce_m_v.vendor.vendorWallet.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorWalletMergerDTO {

    private Long vendorWalletId;
    private double currentAmount;


}
