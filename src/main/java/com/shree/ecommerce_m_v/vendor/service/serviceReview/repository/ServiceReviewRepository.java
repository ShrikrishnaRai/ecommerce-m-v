package com.shree.ecommerce_m_v.vendor.service.serviceReview.repository;

import com.shree.ecommerce_m_v.vendor.service.serviceReview.model.entity.ServiceReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceReviewRepository extends JpaRepository<ServiceReviewEntity, Long> {

    @Query(value="Select * from service_review where service_id_fk=?1", nativeQuery=true)
    List<ServiceReviewEntity> getServiceReviewOfServiceWithServiceId(@Param("serviceId") Long serviceId);

}
