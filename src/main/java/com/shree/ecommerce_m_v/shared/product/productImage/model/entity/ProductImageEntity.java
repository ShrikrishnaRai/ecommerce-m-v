package com.shree.ecommerce_m_v.shared.product.productImage.model.entity;

import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product_image")
public class ProductImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_image_id")
    private Long productImageId;
    private String productImage;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="product_id_fk",referencedColumnName = "product_id")
    private ProductEntity productEntity;
}
