package com.shree.ecommerce_m_v.customer.order.invoice.model.entity;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Invoice")
@Table(name = "invoice")
public class InvoiceEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceId;
    @Column(unique = true)
    private String invoiceNumber;
    private String pdf;
    private String status;

    @OneToOne
    @JoinColumn(name = "order_fk")
    private OrderEntity orderEntity;

    @OneToOne
    @JoinColumn(name = "vendor_fk")
    private VendorEntity vendorEntity;

    @OneToOne
    @JoinColumn(name = "customer_fk")
    private CustomerEntity customerEntity;




}
