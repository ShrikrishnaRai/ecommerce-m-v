package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryBoyPaymentHistoryMergerDTO {

    private long deliveryBoyPaymentHistoryId;
    private double totalAmount;
}
