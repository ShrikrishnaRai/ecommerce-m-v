package com.shree.ecommerce_m_v.deliveryBoy.task.service.mapper;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.task.model.dto.TaskDTO;
import com.shree.ecommerce_m_v.deliveryBoy.task.model.entity.TaskEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class TaskMapper {

    protected TaskEntity toEntity(TaskDTO taskDTO) {
        DeliveryBoyInfoEntity deliveryBoyInfoEntity = new DeliveryBoyInfoEntity();
        if (taskDTO.getDeliveryBoyMergerDTO() != null) {
            deliveryBoyInfoEntity= DeliveryBoyInfoEntity.builder()
                    .deliveryBoyId(taskDTO.getDeliveryBoyMergerDTO().getDeliveryBoyId())
                    .name(taskDTO.getDeliveryBoyMergerDTO().getName())
                    .build();
        }
        return TaskEntity.builder()
                .status(taskDTO.getStatus())
                .deliveryBoy(taskDTO.getDeliveryBoyMergerDTO()!=null? deliveryBoyInfoEntity: null)
                .build();

    }

    protected TaskDTO toDTO(TaskEntity taskEntity) {
        DeliveryBoyMergerDTO deliveryBoyMergerDTO = new DeliveryBoyMergerDTO();
        if (taskEntity.getDeliveryBoy() != null) {
            deliveryBoyMergerDTO= DeliveryBoyMergerDTO.builder()
                    .deliveryBoyId(taskEntity.getDeliveryBoy().getDeliveryBoyId())
                    .name(taskEntity.getDeliveryBoy().getName())
                    .build();
        }
        return TaskDTO.builder()
                .taskId(taskEntity.getTaskId())
                .status(taskEntity.getStatus())
                .deliveryBoyMergerDTO(deliveryBoyMergerDTO)
                .build();

    }

    protected List<TaskDTO> toDTOs(List<TaskEntity> taskEntities) {
        return taskEntities.stream()
                .map(taskEntity -> toDTO(taskEntity))
                .collect(Collectors.toList());
    }


}
