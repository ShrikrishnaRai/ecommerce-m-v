package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.dto.DeliveryBoyWalletMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for saving entity class
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryBoyWalletHistoryDTO {


    private long deliveryBoyWalletHistoryId;
    private double currentAmount;
    private Status status;
    private DeliveryBoyWalletMergerDTO deliveryBoyWalletMergerDTO;


}
