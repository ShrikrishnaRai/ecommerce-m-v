package com.shree.ecommerce_m_v.customer.order.order.model.entity.orderReturn;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="OrderReturn")
@Table(name = "order_return")
public class OrderReturn {

    @Id
    @Column(name = "order_return_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderReturnId;
    private String reason;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_fk")
    private OrderEntity orderEntity;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_fk")
    private CustomerEntity customerEntity;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_fk")
    private ProductEntity productEntity;

}
