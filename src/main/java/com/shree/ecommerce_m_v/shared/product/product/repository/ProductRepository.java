package com.shree.ecommerce_m_v.shared.product.product.repository;

import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    @Query(value = "SELECT * FROM product p WHERE p.product_name like %:name%", nativeQuery = true)
    Page<ProductEntity> getProductWithName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM product p WHERE p.category_entity_fk=?1", nativeQuery = true)
    List<ProductEntity> getProductListByCategories(int categoryId);

}
