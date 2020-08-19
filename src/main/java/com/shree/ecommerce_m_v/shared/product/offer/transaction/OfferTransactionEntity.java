package com.shree.ecommerce_m_v.shared.product.offer.transaction;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.shared.product.offer.model.entity.OfferEntity;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="OfferTransactionEntity")
@Table(name = "offer_transaction")
public class OfferTransactionEntity extends AbstractAuditingEntity {

    @Id
    @Column(name = "offer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "offer_id_fk")
    private OfferEntity offerEntity;

    private long product_id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_entity_fk", referencedColumnName = "customer_id")
    private CustomerEntity customerEntity;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id_fk")
    private OrderEntity orderEntity;


}
