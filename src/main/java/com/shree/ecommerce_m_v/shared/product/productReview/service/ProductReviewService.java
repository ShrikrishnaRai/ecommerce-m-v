package com.shree.ecommerce_m_v.shared.product.productReview.service;


import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.productReview.service.dto.ProductReviewDTO;
import com.shree.ecommerce_m_v.shared.product.productReview.model.entity.ProductReviewEntity;
import com.shree.ecommerce_m_v.shared.product.productReview.repository.ProductReviewRepository;
import com.shree.ecommerce_m_v.shared.product.productReview.service.mapper.ProductReviewMapper;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shree Rai
 * @Date 2/7/20
 */
@Service
public class ProductReviewService extends ProductReviewMapper  {

    @Autowired
    private ProductReviewRepository productReviewRepository;


    public List<ProductReviewDTO> getProductReviews() {
        return toDtoList(productReviewRepository.findAll());
    }


    public ProductReviewDTO getProductReviewById(Long productReviewId) {
        return toDTO(productReviewRepository.getOne(productReviewId));
    }


    public String saveProductReview(ProductReviewDTO productReviewDTO) {
        productReviewRepository.save(toEntity(productReviewDTO));
        return ResponseValue.SAVE_SUCCESS;
    }


    public List<ProductReviewDTO> getReviewOfProductWithProductId(Long productId) {
        return toDtoList(productReviewRepository.getReviewOfProductWithProductId(productId));
    }


    public ProductReviewDTO updateProductReview(Long productId, ProductReviewDTO productReviewDTO) {

        ProductReviewEntity productReviewEntity = productReviewRepository.getOne(productId);

        if (productReviewDTO.getProductMergerDTO() != null) {
            productReviewEntity.setProductEntity(ProductEntity.builder()
                    .productId(productReviewDTO.getProductMergerDTO().getProductId())
                    .productName(productReviewDTO.getProductMergerDTO().getProductName())
                    .build());
        }
        productReviewEntity.setReply(productReviewDTO.getReply());
        productReviewEntity.setReview(productReviewDTO.getReview());
        productReviewEntity.setRating(productReviewDTO.getRating());
        productReviewRepository.saveAndFlush(productReviewEntity);
        return toDTO(productReviewEntity);
    }


    public List<ProductReviewDTO> getReviewOfProductByCustomerId(Long id) {
        return toDtoList(productReviewRepository.getReviewOfProductByCustomerId(id));
    }


    public List<ProductReviewDTO> getReviewOfProductByCustomerIdAndProductId(Long customerId, Long productId) {
        return toDtoList(productReviewRepository.getReviewOfProductByCustomerIdAndProductId(customerId, productId));
    }
}
