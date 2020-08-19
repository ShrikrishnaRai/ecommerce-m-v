package com.shree.ecommerce_m_v.customer.order.order.model.entity;

import com.shree.ecommerce_m_v.shared.product.color.model.entity.ColorEntity;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.size.model.entity.SizeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="OrderDetailEntity")
@Table(name = "order_detail")
public class OrderDetailEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private long orderDetailId;
    private int totalQuantity;
    private double discountValue;
    private double salePrice;
    private double subTotal;

    @ManyToOne
    @JoinColumn(name="order_fk")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name="product_fk")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name="size_fk")
    private SizeEntity sizeEntity;

    @ManyToOne
    @JoinColumn(name="color_fk")
    private ColorEntity colorEntity;

}
