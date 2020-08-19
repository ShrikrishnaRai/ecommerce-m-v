package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.entity;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@Entity(name="DeliveryBoyPaymentHistoryEntity")
@Table(name = "delivery_boy_payment_history")
@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeliveryBoyPaymentHistoryEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_boy_payment_history_id")
    private long deliveryBoyPaymentHistoryId;
    private double totalAmount;
    private Status status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "delivery_boy_id_fk", referencedColumnName = "delivery_boy_id")
    private DeliveryBoyInfoEntity deliveryBoyInfoEntity;


}
