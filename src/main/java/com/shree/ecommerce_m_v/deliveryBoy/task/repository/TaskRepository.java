package com.shree.ecommerce_m_v.deliveryBoy.task.repository;

import com.shree.ecommerce_m_v.deliveryBoy.task.model.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
