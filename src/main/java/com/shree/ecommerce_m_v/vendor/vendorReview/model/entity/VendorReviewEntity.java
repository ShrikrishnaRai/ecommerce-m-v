package com.shree.ecommerce_m_v.vendor.vendorReview.model.entity;

import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="VendorReviewEntity")
@Table(name="vendor_review")
public class VendorReviewEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorReviewId;
    private int rating;
    private String review;
    private String reply;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="vendor_id_fk")
    private VendorEntity vendorEntity;

}
