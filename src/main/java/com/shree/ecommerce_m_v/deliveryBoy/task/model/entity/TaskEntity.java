package com.shree.ecommerce_m_v.deliveryBoy.task.model.entity;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.taskHistory.model.entity.TaskHistoryEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="TaskEntity")
@Table(name = "task")
public class TaskEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long taskId;
    private String status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "delivery_boy_id_fk", referencedColumnName = "delivery_boy_id")
    private DeliveryBoyInfoEntity deliveryBoy;

    @OneToMany(mappedBy = "taskEntity")
    private List<TaskHistoryEntity> taskHistoryEntities= new ArrayList<>();


}
