package com.shree.ecommerce_m_v.vendor.service.service.repository;

import com.shree.ecommerce_m_v.vendor.service.service.model.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    @Query(value = "Select * from service s where s.service_name like %:name%", nativeQuery = true)
    List<ServiceEntity> getServiceByName(@Param("name") String name);

}
