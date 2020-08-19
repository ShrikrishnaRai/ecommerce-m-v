package com.shree.ecommerce_m_v.shared.product.productStock.model.entity;

import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @author Shree Rai
 * @Date 2/7/20
 */

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="ProductStockEntity")
@Table(name = "product_stock")
@EqualsAndHashCode(callSuper = true)
public class ProductStockEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_stock_id")
    private Long productStockId;
    private int totalQuantity;
    private int remainingQuantity;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="product_id_fk" ,referencedColumnName = "product_id")
    private ProductEntity productEntity;


}
