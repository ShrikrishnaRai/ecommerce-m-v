package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.entity;


import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.entity.DeliveryBoyWalletEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="DeliveryBoyWalletHistoryEntity")
@Table(name = "delivery_boy_wallet_history")
public class DeliveryBoyWalletHistoryEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliveryBoyWalletHistoryId;
    private double currentAmount;
    private Status status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "delivery_boy_wallet_fk", referencedColumnName = "delivery_boy_wallet_id")
    private DeliveryBoyWalletEntity deliveryBoyWalletEntity;


}
