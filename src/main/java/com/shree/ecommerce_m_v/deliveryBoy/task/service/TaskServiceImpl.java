package com.shree.ecommerce_m_v.deliveryBoy.task.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.task.model.dto.TaskDTO;
import com.shree.ecommerce_m_v.deliveryBoy.task.model.entity.TaskEntity;
import com.shree.ecommerce_m_v.deliveryBoy.task.repository.TaskRepository;
import com.shree.ecommerce_m_v.deliveryBoy.task.service.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends TaskMapper implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Page<TaskDTO> getAllTasks(int page) {

        Pageable pageable= PageRequest.of(page,10);
        Page<TaskEntity> tasks = taskRepository.findAll(pageable);
        return tasks.map(taskEntity -> toDTO(taskEntity));
    }

    @Override
    public TaskDTO getTaskById(long id) {
        return toDTO(taskRepository.getOne(id));
    }

    @Override
    public Long saveTask(TaskDTO taskDTO) {
        return taskRepository.save(toEntity(taskDTO)).getTaskId();
    }

    @Override
    public TaskDTO updateTask(long id, TaskDTO taskDTO) {
        TaskEntity taskEntity = taskRepository.getOne(id);
        taskEntity.setTaskId(taskDTO.getTaskId());
        taskEntity.setStatus(taskDTO.getStatus());
        if (taskDTO.getDeliveryBoyMergerDTO() != null) {
            taskEntity.setDeliveryBoy(DeliveryBoyInfoEntity.builder()
                    .deliveryBoyId(taskDTO.getDeliveryBoyMergerDTO().getDeliveryBoyId())
                    .name(taskDTO.getDeliveryBoyMergerDTO().getName())
                    .build());
        }
        taskRepository.save(taskEntity);
        return toDTO(taskEntity);
    }

    @Override
    public String deleteTaskById(long id) {

        taskRepository.deleteById(id);
        return "Task with id : " + id + " deleted";
    }
}