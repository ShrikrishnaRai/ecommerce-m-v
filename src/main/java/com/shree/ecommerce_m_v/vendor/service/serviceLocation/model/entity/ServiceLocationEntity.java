package com.shree.ecommerce_m_v.vendor.service.serviceLocation.model.entity;

import com.shree.ecommerce_m_v.vendor.service.serviceProvider.model.entity.ServiceProviderEntity;
import lombok.*;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity(name="ServiceLocationEntity")
@Table(name="service_location")
public class ServiceLocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceLocationId;
    private String city;

    @ManyToOne
    @JoinColumn(name="service_provider_id_fk")
    private ServiceProviderEntity serviceProviderEntity;


}
