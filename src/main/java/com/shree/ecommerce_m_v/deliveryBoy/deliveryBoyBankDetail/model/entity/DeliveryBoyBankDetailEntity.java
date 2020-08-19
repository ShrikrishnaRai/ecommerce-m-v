package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.entity;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "DeliveryBoyBankDetailEntity")
@Table(name = "delivery_boy_bank_detail")
public class DeliveryBoyBankDetailEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bankId;
    private String bankName;
    private String accountHolder;
    private String accountNumber;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "delivery_boy_fk", referencedColumnName = "delivery_boy_id")
    private DeliveryBoyInfoEntity deliveryBoy;


    public DeliveryBoyBankDetailEntity(long bankId, String accountHolder) {
        this.bankId = bankId;
        this.accountHolder = accountHolder;
    }


}

