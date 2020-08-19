package com.shree.ecommerce_m_v.vendor.vendorPackage.model.entity;

import com.shree.ecommerce_m_v.vendor.packageTrans.model.entity.PackageTransEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="PackageEntity")
@Table(name = "package")
public class PackageEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private long packageId;
    private String vendorPackage;
    private double amount;

    @OneToOne
    @JoinColumn(name = "package_trans_id_fk", referencedColumnName = "package_trans_id")
    private PackageTransEntity packageTransEntity;

}
