package com.shree.ecommerce_m_v.vendor.coupon.model.entity;

import com.shree.ecommerce_m_v.customer.couponUser.model.entity.CouponUser;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import com.shree.ecommerce_m_v.vendor.coupon.model.DiscountType;
import com.shree.ecommerce_m_v.vendor.coupon.model.Status;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="CouponEntity")
@Table(name = "coupon")
public class CouponEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long couponId;
    private String couponCode;
    private String name;
    private String description;
    private DiscountType discountType;
    private double discountValue;
    private LocalDate validFrom;
    private LocalDate validTo;
    private int usesPerCoupon;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "vendor_fk", referencedColumnName = "vendor_id")
    private VendorEntity vendorEntity;

    @OneToMany(mappedBy = "couponEntity")
    private List<CouponUser> couponUserList=new ArrayList<>();

}
