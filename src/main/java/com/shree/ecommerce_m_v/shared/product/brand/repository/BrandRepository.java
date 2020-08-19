package com.shree.ecommerce_m_v.shared.product.brand.repository;

import com.shree.ecommerce_m_v.shared.product.brand.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    @Query(value = "SELECT * FROM brands b WHERE b.brand_name like %:brand_name%", nativeQuery = true)
    List<BrandEntity> getBrandWithName(@Param("brand_name") String brand_name);


}
