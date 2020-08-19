package com.shree.ecommerce_m_v.vendor.vendor.repository;

import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
    @Query(value = "SELECT * FROM vendor_info vendor WHERE vendor.vendor_name like %:name%", nativeQuery = true)
    Page<VendorEntity> getVendorWithName(@Param("name") String name, Pageable pageable);

    @Query(value = "Select * from vendor_info where email=?1 or contact_no=?2 or username=?3", nativeQuery = true)
    String findByEmail(@Param("email") String email, @Param("contactNo") String contactNo, @Param("username") String username);

    @Query(value = "select * from vendor_info where email=?1", nativeQuery = true)
    VendorEntity findVendorEntityByEmail(@Param("email") String email);
}
