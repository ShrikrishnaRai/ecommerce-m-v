package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryBoyBankDetailMergerDTO {

    private long bankId;
    private String accountHolder;

}
