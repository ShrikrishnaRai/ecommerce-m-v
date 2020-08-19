package com.shree.ecommerce_m_v.vendor.service.serviceLocation.repository;

import com.shree.ecommerce_m_v.vendor.service.serviceLocation.model.entity.ServiceLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ServiceLocationRepository extends JpaRepository<ServiceLocationEntity,Long> {

    @Query(value="Select * from service_location where service_provider_id_fk=?1",nativeQuery=true)
    List<ServiceLocationEntity> getServiceLocationByServiceProviderId(@Param("serviceProviderId") Long serviceProviderId);
}
