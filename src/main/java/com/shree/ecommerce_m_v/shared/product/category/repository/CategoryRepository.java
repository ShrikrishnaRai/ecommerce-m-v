package com.shree.ecommerce_m_v.shared.product.category.repository;

import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryIdAndName;
import com.shree.ecommerce_m_v.shared.product.category.model.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(value = "SELECT * FROM category c WHERE c.category_name like %:category_name%", nativeQuery = true)
    Page<CategoryEntity> getCategoryWithName(@Param("category_name") String category_name, Pageable pageable);

    List<CategoryIdAndName> getAllBy();
}
