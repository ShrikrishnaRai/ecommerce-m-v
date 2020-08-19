package com.shree.ecommerce_m_v.vendor.service.serviceCategory.model.entity;

import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

/**
 * Reminder add self referencing
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_category")
@Entity(name="ServiceCategoryEntity")
public class ServiceCategoryEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serviceCategoryId;
    private String serviceCategoryName;
    private String serviceDescription;
    private String serviceSlug;


}
