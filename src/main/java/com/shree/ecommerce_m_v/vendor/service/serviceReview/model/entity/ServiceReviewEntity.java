package com.shree.ecommerce_m_v.vendor.service.serviceReview.model.entity;

import com.shree.ecommerce_m_v.vendor.service.service.model.entity.ServiceEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="ServiceReviewEntity")
@Table(name = "service_review")
public class ServiceReviewEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serviceReviewId;
    private int rating;
    private String review;
    private String reply;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "service_id_fk")
    private ServiceEntity service;

}
