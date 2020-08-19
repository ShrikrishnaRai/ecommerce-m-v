package com.shree.ecommerce_m_v.shared.product.productReview.service;

import com.shree.ecommerce_m_v.shared.product.productReview.service.dto.ProductReviewDTO;

import java.util.List;

/**
 * @author Shree Rai
 * @Date 2/7/20
 */
public interface ProductReviewService {
    List<ProductReviewDTO> getProductReviews();

    ProductReviewDTO getProductReviewById(Long productReviewId);

    String saveProductReview(ProductReviewDTO productReviewDTO);

    List<ProductReviewDTO> getReviewOfProductWithProductId(Long productId);

    ProductReviewDTO updateProductReview(Long productId, ProductReviewDTO productReviewDTO);

    List<ProductReviewDTO> getReviewOfProductByCustomerId(Long id);

    List<ProductReviewDTO> getReviewOfProductByCustomerIdAndProductId(Long customerId, Long productId);

}
