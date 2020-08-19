package com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.entity;

import com.shree.ecommerce_m_v.deliveryBoy.task.model.entity.TaskEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="TaskHistoryEntity")
@Table(name = "task_history")
public class TaskHistoryEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_history_id")
    private long taskHistoryId;
    @Column
    private Status status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id_fk", referencedColumnName = "task_id")
    private TaskEntity taskEntity;



}
