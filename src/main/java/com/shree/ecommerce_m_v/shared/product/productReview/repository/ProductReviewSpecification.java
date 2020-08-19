//package com.shree.ecommerce_m_v.shared.product.productReview.repository;
//
//import com.shree.ecommerce_m_v.shared.product.productReview.model.entity.ProductReviewEntity;
//import org.springframework.data.jpa.domain.Specification;
//
//public class ProductReviewSpecification {
//
//
//    public static Specification<ProductReviewEntity> getProductReviewByRating(float rating){
//        return (root, query, cb) -> {
//            return cb.equal(root.get("rating"), rating);
//        };
//    }
//}
