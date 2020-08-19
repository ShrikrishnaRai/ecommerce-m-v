package com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.dto;

import com.shree.ecommerce_m_v.deliveryBoy.task.model.dto.TaskMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskHistoryResponseDTO {


    private long taskHistoryID;
    private Status status;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    private LocalTime timeStamp;
    private TaskMergerDTO taskMergerDTO;


}
