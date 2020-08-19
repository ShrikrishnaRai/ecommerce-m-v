package com.shree.ecommerce_m_v.vendor.vendorReview.repository;

import com.shree.ecommerce_m_v.vendor.vendorReview.model.entity.VendorReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorReviewRepository extends JpaRepository<VendorReviewEntity,Long> {

    @Query(value="Select * from vendor_review where vendor_id_fk=?1" ,nativeQuery=true)
    List<VendorReviewEntity> getReviewOfVendorWithVendorId(@Param("vendorId") Long vendorId);
}
