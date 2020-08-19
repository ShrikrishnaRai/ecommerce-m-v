package com.shree.ecommerce_m_v.customer.customerWallet.model.dto;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWalletDTO {

    private long customerWalletId;
    private double currentAmount;

    private CustomerMergerDTO customerMergerDTO;


}
