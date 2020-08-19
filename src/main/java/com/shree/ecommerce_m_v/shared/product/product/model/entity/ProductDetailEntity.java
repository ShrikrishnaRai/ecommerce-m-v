package com.shree.ecommerce_m_v.shared.product.product.model.entity;

import com.shree.ecommerce_m_v.shared.product.color.model.entity.ColorEntity;
import com.shree.ecommerce_m_v.shared.product.size.model.entity.SizeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ProductDetail")
@Table(name = "product_detail")
public class ProductDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_detail_id")
    private Long productDetailId;

    @OneToOne
    @JoinColumn(name = "product_id_fk", referencedColumnName = "product_id")
    private ProductEntity productEntity;

    @OneToMany(mappedBy = "productEntityList")
    private List<SizeEntity> sizeEntityList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "colour_fk")
    private ColorEntity colorEntity;

    private int totalQuantity;
    private int remainingQuantity;
    private boolean isAvailable;


}
