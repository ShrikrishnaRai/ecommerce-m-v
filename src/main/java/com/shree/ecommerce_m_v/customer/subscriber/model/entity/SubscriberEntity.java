package com.shree.ecommerce_m_v.customer.subscriber.model.entity;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="SubscriberEntity")
@Table(name="subscriber")
public class SubscriberEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriberId;
    @Email(message = "Please provide a valid email")
    @Column(unique = true ,name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name="customer_id_fk")
    private CustomerEntity customerEntity;

}
