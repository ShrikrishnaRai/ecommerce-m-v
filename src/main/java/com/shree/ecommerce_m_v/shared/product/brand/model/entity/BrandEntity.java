package com.shree.ecommerce_m_v.shared.product.brand.model.entity;

import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
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
@Entity(name = "BrandEntity")
@Table(name = "brands")
public class BrandEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private long brandId;
    private String brandName;
    private String companyName;
    @Column(columnDefinition = "LONGTEXT")
    private String brandImage;
    private String status;
    private String slug;

    @OneToMany(mappedBy = "brandEntity", cascade = {CascadeType.MERGE})
    private List<ProductEntity> productEntityList = new ArrayList<>();

    public void addBrandToProduct(ProductEntity productEntity) {
        productEntityList.add(productEntity);
        productEntity.setBrandEntity(this);
    }

    public void removeBrandFromProduct(ProductEntity productEntity) {
        productEntityList.remove(productEntity);
        productEntity.setBrandEntity(null);
    }
}
