package com.shree.ecommerce_m_v.deliveryBoy.taskHistory.service.mapper;

import com.shree.ecommerce_m_v.deliveryBoy.task.model.dto.TaskMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.task.model.entity.TaskEntity;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.dto.TaskHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.dto.TaskHistoryResponseDTO;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.entity.TaskHistoryEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class TaskHistoryMapper {

    protected TaskHistoryEntity toEntity(TaskHistoryDTO taskHistoryDTO) {
        TaskEntity taskEntity= new TaskEntity();
        if(taskHistoryDTO.getTaskMergerDTO()!=null){
            taskEntity= TaskEntity.builder()
                    .taskId(taskHistoryDTO.getTaskMergerDTO().getTaskId())
                    .status(taskHistoryDTO.getTaskMergerDTO().getStatus())
                    .build();
        }

      return TaskHistoryEntity.builder()
              .status(taskHistoryDTO.getStatus())
              .taskEntity(taskHistoryDTO.getTaskMergerDTO()!=null? taskEntity:null)
              .build();
    }

    protected TaskHistoryResponseDTO toDTO(TaskHistoryEntity taskHistoryEntity) {

        TaskMergerDTO taskMergerDTO= new TaskMergerDTO();
        if(taskHistoryEntity.getTaskEntity()!=null){
            taskMergerDTO= TaskMergerDTO.builder()
                    .taskId(taskHistoryEntity.getTaskEntity().getTaskId())
                    .status(taskHistoryEntity.getTaskEntity().getStatus())
                    .build();
        }

        return TaskHistoryResponseDTO.builder()
                .taskHistoryID(taskHistoryEntity.getTaskHistoryId())
                .status(taskHistoryEntity.getStatus())
                .dateCreated(taskHistoryEntity.getDateCreated())
                .dateModified(taskHistoryEntity.getDateModified())
                .timeStamp(taskHistoryEntity.getTimeStamp())
                .taskMergerDTO(taskMergerDTO)
                .build();
    }

    protected List<TaskHistoryResponseDTO> toDTOs(List<TaskHistoryEntity> taskHistoryEntities) {
        return taskHistoryEntities.stream()
                .map(taskHistoryEntity -> toDTO(taskHistoryEntity))
                .collect(Collectors.toList());
    }
}
