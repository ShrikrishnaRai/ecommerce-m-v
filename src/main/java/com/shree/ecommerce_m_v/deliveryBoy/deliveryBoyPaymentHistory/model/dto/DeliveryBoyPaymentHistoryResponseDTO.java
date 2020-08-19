package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.entity.Status;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryBoyPaymentHistoryResponseDTO {

    private long deliveryBoyPaymentHistoryId;
    private double totalAmount;
    private Status status;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    private LocalTime timeStamp;

    private DeliveryBoyMergerDTO deliveryBoyMergerDTO;


}
