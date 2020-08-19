package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.dto.DeliveryBoyWalletMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO class for sending response
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryBoyWalletHistoryResponseDTO {


    private long deliveryBoyWalletHistoryId;
    private double currentAmount;
    private Status status;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    private LocalTime timeStamp;
    private DeliveryBoyWalletMergerDTO deliveryBoyWalletMergerDTO;


}
