package com.shree.ecommerce_m_v.vendor.service.service.model.entity;

import com.shree.ecommerce_m_v.vendor.service.serviceProvider.model.entity.ServiceProviderEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceReview.model.entity.ServiceReviewEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="ServiceEntity")
@Table(name = "service")
public class ServiceEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serviceId;
    private String serviceName;
    private String description;
    private Long parentServiceId;
    private String slug;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "service_provider_id_fk")
    private ServiceProviderEntity serviceProviderEntity;

    @OneToMany(mappedBy = "service")
    private List<ServiceReviewEntity> serviceReviewEntities= new ArrayList<>();




}
