package com.shree.ecommerce_m_v.vendor.vendorWallet.model.entity;

import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="VendorWalletEntity")
@Table(name="vendor_wallet")
public class VendorWalletEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorWalletId;
    private double currentAmount;

    @OneToOne(mappedBy = "vendorWalletEntity",cascade = CascadeType.MERGE)
    private VendorEntity vendorEntity;
}
