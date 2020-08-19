package com.shree.ecommerce_m_v.customer.couponUser.model.entity;

import com.shree.ecommerce_m_v.vendor.coupon.model.entity.CouponEntity;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "coupon_user")
@Entity(name="CouponUser")
public class CouponUser {
    @Id
    @Column(name = "coupon_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long couponUserId;
    private LocalDate usedDate;

    @ManyToOne
    @JoinColumn(name = "coupon_id_fk" ,referencedColumnName = "coupon_id")
    private CouponEntity couponEntity;

    @ManyToMany
    @JoinColumn(name="customer_id_fk",referencedColumnName = "customer_id")
    private List<CustomerEntity> customerEntityList= new ArrayList<>();

    @OneToOne
    @JoinColumn(name="order_id_fk")
    private OrderEntity orderEntity;

}
