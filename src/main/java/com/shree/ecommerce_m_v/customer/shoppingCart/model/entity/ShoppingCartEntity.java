package com.shree.ecommerce_m_v.customer.shoppingCart.model.entity;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ShoppingCartEntity")
@Table(name = "shopping_cart")
public class ShoppingCartEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingCartId;
    private int quantity;
    private double grandTotal;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id_fk", referencedColumnName = "product_id")
    private ProductEntity productEntity;

    @OneToOne
    @JoinColumn(name = "customer_id_fk", referencedColumnName = "customer_id")
    private CustomerEntity customerEntity;

}
