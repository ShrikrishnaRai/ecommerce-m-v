package com.shree.ecommerce_m_v.deliveryBoy.taskHistory.service;

import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.dto.TaskHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.dto.TaskHistoryResponseDTO;
import org.springframework.data.domain.Page;

public interface TaskHistoryService {

    Page<TaskHistoryResponseDTO> getAllTaskHistory(int page);

    TaskHistoryResponseDTO getTaskHistoryById(long id);

    String saveTaskHistory(TaskHistoryDTO taskHistoryDTO);

    TaskHistoryResponseDTO updateTaskHistoryById(long id, TaskHistoryDTO taskHistoryDTO);

    String deleteTaskHistoryById(long id);

}
