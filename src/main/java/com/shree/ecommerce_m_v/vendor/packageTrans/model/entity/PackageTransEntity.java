package com.shree.ecommerce_m_v.vendor.packageTrans.model.entity;

import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.vendor.vendorPackage.model.entity.PackageEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="PackageTransEntity")
@Table(name = "vendor_package_trans")
public class PackageTransEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_trans_id")
    private Long packageTransId;

    @OneToOne
    @JoinColumn(name = "package_id_fk", referencedColumnName = "package_id")
    private PackageEntity packageEntity;

    @OneToOne
    @JoinColumn(name = "vendor_id_fk", referencedColumnName = "vendor_id")
    private VendorEntity vendorEntity;

    private Status status;

}
