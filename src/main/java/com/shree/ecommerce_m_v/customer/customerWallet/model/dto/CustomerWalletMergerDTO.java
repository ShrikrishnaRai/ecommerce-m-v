package com.shree.ecommerce_m_v.customer.customerWallet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWalletMergerDTO {

    private long customerWalletId;
    private double currentAmount;
}
