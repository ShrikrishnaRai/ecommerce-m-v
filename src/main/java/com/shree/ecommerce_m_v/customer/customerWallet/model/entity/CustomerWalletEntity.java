package com.shree.ecommerce_m_v.customer.customerWallet.model.entity;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="CustomerWalletEntity")
@Table(name = "customer_wallet")
public class CustomerWalletEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_wallet_id")
    private long customerWalletId;
    private double currentAmount;

    @OneToOne(cascade = CascadeType.MERGE, mappedBy = "customerWalletEntity")
    private CustomerEntity customerEntity;


}
