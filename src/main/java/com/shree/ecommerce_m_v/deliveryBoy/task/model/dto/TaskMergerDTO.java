package com.shree.ecommerce_m_v.deliveryBoy.task.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskMergerDTO {

    private Long taskId;
    private String status;

}
