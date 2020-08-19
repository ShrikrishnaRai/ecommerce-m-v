package com.shree.ecommerce_m_v.customer.preBooking.model.entity;

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
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="PreBookingEntity")
@Table(name="pre_booking")
public class PreBookingEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preBookingId;
    private int quantity;
    private double price;
    private double totalPrice;
    private Status status;

    @ManyToMany(cascade = CascadeType.MERGE )
    @JoinTable(name="product_prebooking" ,
            joinColumns = @JoinColumn(name="prebooking_id"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<ProductEntity> productEntities= new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="customer_id_fk" ,referencedColumnName = "customer_id")
    private CustomerEntity customerEntity;



}
