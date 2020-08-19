package com.shree.ecommerce_m_v.customer.wishlist.model.entity;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="WishlistEntity")
@Table(name="wishlist")
public class WishlistEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishListId;

    @OneToOne
    @JoinColumn(name="customer_id_fk" ,referencedColumnName = "customer_id")
    private CustomerEntity customerEntity;

    @ManyToMany
    @JoinTable(name="product_wishlist",
    joinColumns = @JoinColumn(name="wish_list_id"),
    inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<ProductEntity> productEntityList= new ArrayList<>();

}
