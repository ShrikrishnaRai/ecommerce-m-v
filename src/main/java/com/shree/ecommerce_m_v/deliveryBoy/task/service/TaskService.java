package com.shree.ecommerce_m_v.deliveryBoy.task.service;

import com.shree.ecommerce_m_v.deliveryBoy.task.model.dto.TaskDTO;
import org.springframework.data.domain.Page;

public interface TaskService {

    Page<TaskDTO> getAllTasks(int page);

    TaskDTO getTaskById(long id);

    Long saveTask(TaskDTO taskDTO);

    TaskDTO updateTask(long id, TaskDTO taskDTO);

    String deleteTaskById(long id);

}
