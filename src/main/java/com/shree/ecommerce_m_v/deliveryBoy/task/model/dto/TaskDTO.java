package com.shree.ecommerce_m_v.deliveryBoy.task.model.dto;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private long taskId;
    private String status;
    private DeliveryBoyMergerDTO deliveryBoyMergerDTO;


}
