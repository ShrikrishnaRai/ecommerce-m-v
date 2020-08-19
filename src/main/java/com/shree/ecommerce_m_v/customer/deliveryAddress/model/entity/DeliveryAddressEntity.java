package com.shree.ecommerce_m_v.customer.deliveryAddress.model.entity;

import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity(name="DeliveryAddressEntity")
@Table(name = "delivery_address")
public class DeliveryAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliveryAddressId;
    private String street;
    private String city;
    private String district;
    private String state;
    private String contactNo;
    @OneToOne(mappedBy = "deliveryAddressEntity")
    private OrderEntity orderEntity;

}
