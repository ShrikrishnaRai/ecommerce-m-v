package com.shree.ecommerce_m_v.customer.order.deliveryRate.model.entity;

import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="DeliveryRateEntity")
@Table(name = "delivery_rate")
public class DeliveryRateEntity extends AbstractAuditingEntity {

    @Id
    @Column(name="delivery_rate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryRateId;
    private String city;
    private double rate;
    @OneToOne(mappedBy = "deliveryRateEntity")
    private OrderEntity orderEntity;

}
