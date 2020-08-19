package com.shree.ecommerce_m_v.shared.product.category.model.entity;

import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="CategoryEntity")
@Table(name = "category")
public class CategoryEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;
    private String categoryName;
    private String parentId;
    private String categoryImage;
    private String description;

    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.MERGE)
    private List<ProductEntity> productEntityList = new ArrayList<>();


    public void addCategoryToProduct(ProductEntity productEntity){
        productEntityList.add(productEntity);
        productEntity.setCategoryEntity(this);
    }

    public void removeCategoryFromProduct(ProductEntity productEntity){
        productEntityList.remove(productEntity);
        productEntity.setCategoryEntity(null);
    }


}
