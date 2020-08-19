package com.shree.ecommerce_m_v.vendor.commission.model.entity;

import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="CommissionTypeEntity")
@Table(name = "commission_type")
public class CommissionTypeEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commission_type_id")
    private int commissionTypeId;

    private String commissionDescription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private VendorEntity vendorEntity;



}
