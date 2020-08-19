package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.dto;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryBoyBankDetailDTO {

    private long bankId;
    private String bankName;
    private String accountHolder;
    private String accountNumber;

    private DeliveryBoyMergerDTO deliveryBoyMergerDTO;


}
