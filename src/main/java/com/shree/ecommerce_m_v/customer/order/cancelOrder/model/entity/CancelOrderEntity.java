package com.shree.ecommerce_m_v.customer.order.cancelOrder.model.entity;

import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="CancelOrderEntity")
@Table(name = "cancel_order")
public class CancelOrderEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cancelOrderId;
    private String reason;

    /**
     * @OneToOne(cascade = CascadeType.MERGE)
     * @JoinColumn(name = "order_id_fk")
     * private OrderEntity orderEntity;
     */



}
