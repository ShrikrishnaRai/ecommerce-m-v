package com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.dto;

import com.shree.ecommerce_m_v.deliveryBoy.task.model.dto.TaskMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.entity.Status;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskHistoryDTO {

    private long taskHistoryId;
    private Status status;
    private TaskMergerDTO taskMergerDTO;


}
