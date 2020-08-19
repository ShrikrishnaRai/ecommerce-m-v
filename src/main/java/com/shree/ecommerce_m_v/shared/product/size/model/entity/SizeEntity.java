package com.shree.ecommerce_m_v.shared.product.size.model.entity;

import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderDetailEntity;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductDetailEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shree Rai
 * @Date 2/7/20
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SizeEntity")
@Table(name = "size")
public class SizeEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Long sizeId;
    private String size;

    @ManyToOne
    @JoinColumn(name = "product_detail_entity_fk", referencedColumnName = "product_detail_id")
    ProductDetailEntity productEntityList;

    @OneToMany(mappedBy = "sizeEntity")
    List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();


}
