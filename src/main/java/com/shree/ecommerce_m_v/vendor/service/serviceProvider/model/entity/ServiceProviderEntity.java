package com.shree.ecommerce_m_v.vendor.service.serviceProvider.model.entity;

import com.shree.ecommerce_m_v.vendor.service.service.model.entity.ServiceEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceCategory.model.entity.ServiceCategoryEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import com.shree.ecommerce_m_v.vendor.service.serviceLocation.model.entity.ServiceLocationEntity;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity(name="ServiceProviderEntity")
@Table(name = "service_provider_info")
public class ServiceProviderEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceProviderId;

    @OneToOne
    @JoinColumn(name="vendor_id_fk")
    private VendorEntity vendorEntity;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="service_category_id_fk")
    private ServiceCategoryEntity serviceCategoryEntity;

    @OneToMany(mappedBy = "serviceProviderEntity")
    private List<ServiceEntity> serviceEntities= new ArrayList<>();

    @OneToMany(mappedBy = "serviceProviderEntity",cascade = CascadeType.MERGE)
    private List<ServiceLocationEntity> serviceLocationEntities= new ArrayList<>();

    public void addServiceProviderToService(ServiceEntity serviceEntity){
        serviceEntities.add(serviceEntity);
        serviceEntity.setServiceProviderEntity(this);
    }

    public void addServiceProviderToServiceLocation(ServiceLocationEntity serviceLocationEntity){
        serviceLocationEntities.add(serviceLocationEntity);
        serviceLocationEntity.setServiceProviderEntity(this);
    }

}
