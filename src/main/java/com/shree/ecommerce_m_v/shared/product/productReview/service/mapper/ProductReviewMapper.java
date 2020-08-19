package com.shree.ecommerce_m_v.shared.product.productReview.service.mapper;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.productReview.service.dto.ProductReviewDTO;
import com.shree.ecommerce_m_v.shared.product.productReview.model.entity.ProductReviewEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ProductReviewMapper {
    protected ProductReviewDTO toDTO(ProductReviewEntity productReviewEntity) {
        ProductMergerDTO productMergerDTO = new ProductMergerDTO();
        if (productReviewEntity.getProductEntity() != null) {
            productMergerDTO = ProductMergerDTO.builder()
                    .productId(productReviewEntity.getProductEntity().getProductId())
                    .productName(productReviewEntity.getProductEntity().getProductName())
                    .build();
        }
        return ProductReviewDTO.builder()
                .id(productReviewEntity.getProductReviewId())
                .rating(productReviewEntity.getRating())
                .customerMergerDTO(productReviewEntity.getCustomerEntity() != null ? CustomerMergerDTO.builder()
                        .id(productReviewEntity.getCustomerEntity().getCustomerId())
                        .username(productReviewEntity.getCustomerEntity().getUsername())
                        .customerImageUrl(productReviewEntity.getCustomerEntity().getImage())
                        .build() : null)
                .review(productReviewEntity.getReview())
                .reply(productReviewEntity.getReply())
                .productMergerDTO(productMergerDTO)
                .build();
    }

    protected ProductReviewEntity toEntity(ProductReviewDTO productReviewDTO) {

        ProductEntity productEntity = new ProductEntity();
        if (productReviewDTO.getProductMergerDTO() != null) {
            productEntity = ProductEntity.builder()
                    .productId(productReviewDTO.getProductMergerDTO().getProductId())
                    .productName(productReviewDTO.getProductMergerDTO().getProductName())
                    .build();
        }

        return ProductReviewEntity.builder()
                .rating(productReviewDTO.getRating())
                .review(productReviewDTO.getReview())
                .reply(productReviewDTO.getReply())
                .productEntity(productReviewDTO.getProductMergerDTO() != null ? productEntity : null)
                .customerEntity(productReviewDTO.getCustomerMergerDTO() != null ? CustomerEntity.builder()
                        .customerId(productReviewDTO.getCustomerMergerDTO().getId())
                        .username(productReviewDTO.getCustomerMergerDTO().getUsername())
                        .build() : null)
                .build();

    }

    protected List<ProductReviewDTO> toDtoList(List<ProductReviewEntity> productEntityList) {
        return productEntityList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

