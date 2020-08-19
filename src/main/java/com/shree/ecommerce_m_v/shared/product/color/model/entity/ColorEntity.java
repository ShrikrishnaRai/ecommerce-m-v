package com.shree.ecommerce_m_v.shared.product.color.model.entity;

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
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="ColorEntity")
@Table(name = "color")
public class ColorEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long colorId;
    private String colorCode;
    private String colorName;

    @OneToMany(mappedBy = "colorEntity")
    private List<ProductDetailEntity> productDetailEntities = new ArrayList<>();

    @OneToMany(mappedBy = "colorEntity")
    List<OrderDetailEntity> orderDetailEntities= new ArrayList<>();


}
