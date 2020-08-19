package com.shree.ecommerce_m_v.customer.order.order.model.entity;

import com.shree.ecommerce_m_v.customer.couponUser.model.entity.CouponUser;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.deliveryAddress.model.entity.DeliveryAddressEntity;
import com.shree.ecommerce_m_v.customer.order.deliveryRate.model.entity.DeliveryRateEntity;
import com.shree.ecommerce_m_v.customer.order.invoice.model.entity.InvoiceEntity;
import com.shree.ecommerce_m_v.shared.product.offer.transaction.OfferTransactionEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "OrderEntity")
@Table(name = "order_table")
public class OrderEntity extends AbstractAuditingEntity {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String deliveryInstruction;
    private double couponAmount = 0;
    private double discountAmount=0;
    private double discountPercentage=0;
    private double totalAmount;
    private double subTotal;
    private TaxType taxType;
    private LocalDate deliveryDate;
    private double taxValue;
    private double extraCharge;
    private OrderStatus orderStatus;

    @OneToOne(mappedBy = "orderEntity")
    private OfferTransactionEntity offerTransactionEntity;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "delivery_address_fk")
    private DeliveryAddressEntity deliveryAddressEntity;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "delivery_rate_fk")
    private DeliveryRateEntity deliveryRateEntity;

    @OneToOne(mappedBy = "orderEntity")
    private InvoiceEntity invoiceEntity;

    @OneToMany(mappedBy = "orderEntity", cascade = {CascadeType.PERSIST},
            orphanRemoval = true)
    private List<OrderDetailEntity> orderDetailEntity = new ArrayList<>();

    @OneToOne(mappedBy = "orderEntity")
    private CouponUser couponUser;

    @ManyToOne
    @JoinColumn(name = "customer_id_fk")
    private CustomerEntity customerEntity;
}
