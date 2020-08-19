package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.entity;

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
@Entity(name="DeliveryBoyDocumentEntity")
@Table(name = "delivery_boy_document")
public class DeliveryBoyDocumentEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_boy_document_id")
    private long deliveryBoyDocumentId;
    private String licenseImage;
    private String citizenshipImage;
    private String bluebookImage;
    private String vehicleImage;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "delivery_boy_fk", referencedColumnName = "delivery_boy_id")
    private DeliveryBoyInfoEntity deliveryBoy;

}
