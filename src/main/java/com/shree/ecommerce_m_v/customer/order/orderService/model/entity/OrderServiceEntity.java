package com.shree.ecommerce_m_v.customer.order.orderService.model.entity;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="OrderServiceEntity")
@Table(name = "order_service")
public class OrderServiceEntity extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 7733930322412404549L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_service_id")
    private long orderServiceId;
    private String street;
    private String city;
    private String district;
    private String state;
    private LocalDate serviceDate;
    private LocalTime serviceTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id_fk")
    private CustomerEntity customerEntity;


}
