package com.shree.ecommerce_m_v.customer.order.orderStatus.model.entity;

import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="OrderStatusEntity")
@Table(name = "order_status")
public class OrderStatusEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderStatusId;
    private String orderName;

}
