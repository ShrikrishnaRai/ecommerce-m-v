package com.shree.ecommerce_m_v.shared.product.productBundle.model.entity;

import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.productBundle.model.Status;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="ProductBundleEntity")
@Table(name = "product_bundle")
public class ProductBundleEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bundle_id")
    private Long productBundleId;
    private String productBundleCode;
    private String productBundleName;
    private double productBundlePrice;
    private Status status;
    private String tag;
    private boolean isTaxable;
    private double taxAmount;
    private double totalQuantity;
    private int remainingQuantity;

    @ManyToMany(mappedBy = "productBundleEntityList",cascade = CascadeType.MERGE)
    private List<ProductEntity> productEntityList = new ArrayList<>();

    public void addProduct(ProductEntity productEntity) {
        productEntityList.add(productEntity);
        productEntity.getProductBundleEntityList().add(this);
    }

    public void removeProduct(ProductEntity productEntity) {
        productEntityList.remove(productEntity);
        productEntity.getProductBundleEntityList().remove(this);
    }

}
