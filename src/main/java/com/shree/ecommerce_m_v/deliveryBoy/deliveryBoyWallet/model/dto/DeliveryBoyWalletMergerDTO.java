package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryBoyWalletMergerDTO {

    private long deliveryBoyWalletId;
    private double currentAmount;

}
