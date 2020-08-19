package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.entity;


import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.entity.DeliveryBoyWalletHistoryEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="DeliveryBOyWalletEntity")
@Table(name = "delivery_boy_wallet")
public class DeliveryBoyWalletEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_boy_wallet_id")
    private long deliveryBoyWalletId;
    private double currentAmount;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "delivery_boy_id")
    private DeliveryBoyInfoEntity deliveryBoy;

    @OneToMany(mappedBy = "deliveryBoyWalletEntity")
    private List<DeliveryBoyWalletHistoryEntity> deliveryBoyWalletHistories= new ArrayList<>();


    public DeliveryBoyWalletEntity(long deliveryBoyWalletId, double currentAmount) {

        this.deliveryBoyWalletId = deliveryBoyWalletId;
        this.currentAmount = currentAmount;
    }
}
