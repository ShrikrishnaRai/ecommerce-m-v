package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.dto;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto.DeliveryBoyWalletHistoryMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryBoyWalletDTO {

    private long deliveryBoyWalletId;
    private double currentAmount;
    private DeliveryBoyMergerDTO deliveryBoyMergerDTO;

    private List<DeliveryBoyWalletHistoryMergerDTO> deliveryBoyWalletHistoryMergerDTOS = new ArrayList<>();

}
