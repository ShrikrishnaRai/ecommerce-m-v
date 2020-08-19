package com.shree.ecommerce_m_v.shared.product.offer.product.model.entity;

import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="OfferProductEntity")
@Table(name = "offer_product")
public class OfferProductEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long offerProductId;

    
}
