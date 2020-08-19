package com.shree.ecommerce_m_v.vendor.service.serviceCategory.repository;

import com.shree.ecommerce_m_v.vendor.service.serviceCategory.model.entity.ServiceCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategoryEntity, Long> {

    @Query(value = "Select * from service_category s where s.service_category_name like %:name%", nativeQuery = true)
    Page<ServiceCategoryEntity> getServiceCategoryByName(@Param("name") String name, Pageable pageable);
}
