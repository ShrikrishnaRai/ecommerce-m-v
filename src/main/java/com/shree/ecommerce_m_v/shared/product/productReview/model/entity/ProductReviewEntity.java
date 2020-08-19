package com.shree.ecommerce_m_v.shared.product.productReview.model.entity;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Shree Rai
 * @Date 2/7/20
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ProductReviewEntity")
@Table(name = "product_review")
public class ProductReviewEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_review_id")
    private Long productReviewId;
    private float rating;
    private String review;
    private String reply;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id_fk", referencedColumnName = "product_id")
    private ProductEntity productEntity;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id_fk", referencedColumnName = "customer_id")
    @NotNull
    private CustomerEntity customerEntity;

}
