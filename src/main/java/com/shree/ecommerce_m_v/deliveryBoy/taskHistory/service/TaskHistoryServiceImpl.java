package com.shree.ecommerce_m_v.deliveryBoy.taskHistory.service;

import com.shree.ecommerce_m_v.deliveryBoy.task.model.entity.TaskEntity;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.dto.TaskHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.dto.TaskHistoryResponseDTO;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.entity.TaskHistoryEntity;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.repository.TaskHistoryRepository;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.service.mapper.TaskHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskHistoryServiceImpl extends TaskHistoryMapper implements TaskHistoryService {

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;

    @Override
    public Page<TaskHistoryResponseDTO> getAllTaskHistory(int page) {

        Pageable pageable= PageRequest.of(page,10);
        Page<TaskHistoryEntity> taskHistoryEntities = taskHistoryRepository.findAll(pageable);
        return taskHistoryEntities.map(taskHistoryEntity -> toDTO(taskHistoryEntity));
    }

    @Override
    public TaskHistoryResponseDTO getTaskHistoryById(long id) {
        return toDTO(taskHistoryRepository.getOne(id));
    }

    @Override
    public String saveTaskHistory(TaskHistoryDTO taskHistoryDTO) {
        taskHistoryRepository.save(toEntity(taskHistoryDTO));
        return "Task History added successfully";
    }

    @Override
    public TaskHistoryResponseDTO updateTaskHistoryById(long id, TaskHistoryDTO taskHistoryDTO) {
        TaskHistoryEntity taskHistoryEntity = taskHistoryRepository.getOne(id);
        taskHistoryEntity.setTaskHistoryId(taskHistoryDTO.getTaskHistoryId());
        taskHistoryEntity.setStatus(taskHistoryDTO.getStatus());
        if (taskHistoryDTO.getTaskMergerDTO() != null) {
            taskHistoryEntity.setTaskEntity(TaskEntity.builder()
                    .taskId(taskHistoryDTO.getTaskMergerDTO().getTaskId())
                    .status(taskHistoryDTO.getTaskMergerDTO().getStatus())
                    .build());
        }
        taskHistoryRepository.save(taskHistoryEntity);
        return toDTO(taskHistoryEntity);
    }

    @Override
    public String deleteTaskHistoryById(long id) {

        taskHistoryRepository.deleteById(id);
        return "Task History with id :" + id + " is deleted";
    }
}
