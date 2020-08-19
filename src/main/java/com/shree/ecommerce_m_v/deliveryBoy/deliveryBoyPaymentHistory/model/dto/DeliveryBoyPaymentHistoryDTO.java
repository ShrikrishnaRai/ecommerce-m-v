package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryBoyPaymentHistoryDTO {

    private long deliveryBoyPaymentHistoryId;
    private double totalAmount;
    private Status status;

    private DeliveryBoyMergerDTO deliveryBoyMergerDTO;

}
