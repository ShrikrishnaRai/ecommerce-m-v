package com.shree.ecommerce_m_v.shared.product.productReview.repository;

import com.shree.ecommerce_m_v.shared.product.productReview.model.entity.ProductReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Shree Rai
 * @Date 2/7/20
 */
@Repository
@Transactional
public interface ProductReviewRepository extends JpaRepository<ProductReviewEntity, Long>,
        JpaSpecificationExecutor<ProductReviewEntity> {

    @Query(value = "select * from product_review where product_id_fk=?1", nativeQuery = true)
    List<ProductReviewEntity> getReviewOfProductWithProductId(Long id);

    @Query(value = "select * from product_review where customer_id_fk=?1", nativeQuery = true)
    List<ProductReviewEntity> getReviewOfProductByCustomerId(Long id);

    @Query(value = "select * from product_review where customer_id_fk=?1 && product_id_fk=?2", nativeQuery = true)
    List<ProductReviewEntity> getReviewOfProductByCustomerIdAndProductId(Long customerId, Long productId);
}
