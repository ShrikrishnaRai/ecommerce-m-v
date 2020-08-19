package com.shree.ecommerce_m_v.shared.product.offer.model.entity;

import com.shree.ecommerce_m_v.shared.product.offer.transaction.OfferTransactionEntity;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "offer")
@Entity(name="OfferEntity")
public class OfferEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Long offerId;
    private String offerName;
    private String description;
    private LocalDate validFrom;
    private LocalDate validTo;
    private String status;
    private double discountValue;
    private String discountType;
    private double minimumOrder;

    @OneToOne(mappedBy = "offerEntity")
    private OfferTransactionEntity offerTransactionEntity;

    @ManyToOne
    @JoinColumn(name = "vendor_fk", referencedColumnName = "vendor_id")
    private VendorEntity vendorEntity;

    @OneToMany(mappedBy = "offerEntity",cascade = CascadeType.MERGE)
    private List<ProductEntity> productEntityList = new ArrayList<>();

    public void addOfferToProduct(ProductEntity productEntity){
        productEntityList.add(productEntity);
        productEntity.setOfferEntity(this);
    }

    public void removeOfferFromProduct(ProductEntity productEntity){
        productEntityList.remove(productEntity);
        productEntity.setOfferEntity(null);
    }

}
