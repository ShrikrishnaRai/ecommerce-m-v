package com.shree.ecommerce_m_v.customer.complain.model.entity;

import com.shree.ecommerce_m_v.customer.complain.model.Status;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="ComplainEntity")
@Table(name = "complain")
public class ComplainEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long complainId;
    private String topic;
    private String message;
    private String reply;
    private Status status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id_fk")
    private CustomerEntity customerEntity;


}
