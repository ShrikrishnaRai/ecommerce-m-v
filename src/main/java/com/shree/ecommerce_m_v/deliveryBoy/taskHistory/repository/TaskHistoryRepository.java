package com.shree.ecommerce_m_v.deliveryBoy.taskHistory.repository;

import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.entity.TaskHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskHistoryRepository extends JpaRepository<TaskHistoryEntity, Long> {

}
