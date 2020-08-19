package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class as a merger for integration in other DTOs
 * */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryBoyWalletHistoryMergerDTO {

    private long deliveryBoyWalletHistoryId;
    private double currentAmount;
}
