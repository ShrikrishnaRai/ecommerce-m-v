package com.shree.ecommerce_m_v.customer.shoppingCart.service.mapper;


import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.shoppingCart.model.entity.ShoppingCartEntity;
import com.shree.ecommerce_m_v.customer.shoppingCart.service.DTO.ShoppingCartDTO;
import com.shree.ecommerce_m_v.customer.shoppingCart.service.DTO.ShoppingCartResponseDTO;
import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.DTO.ProductResponseMergedDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ShoppingCartMapper {

    protected ShoppingCartEntity toEntity(ShoppingCartDTO shoppingCartDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        if (shoppingCartDTO.getCustomerMergerDTO() != null) {
            customerEntity = CustomerEntity.builder()
                    .customerId(shoppingCartDTO.getCustomerMergerDTO().getId())
                    .username(shoppingCartDTO.getCustomerMergerDTO().getUsername())
                    .build();
        }

        return ShoppingCartEntity.builder()
                .quantity(shoppingCartDTO.getQuantity())
                .grandTotal(shoppingCartDTO.getGrandTotal())
                .customerEntity(shoppingCartDTO.getCustomerMergerDTO() != null ? customerEntity : null)
                .productEntity(ProductEntity
                        .builder()
                        .productId(shoppingCartDTO.getProductMergerDTO().getProductId())
                        .productName(shoppingCartDTO.getProductMergerDTO().getProductName())
                        .build())
                .build();
    }


    protected ShoppingCartDTO toDTO(ShoppingCartEntity shoppingCartEntity) {
        CustomerMergerDTO customerMergerDTO = new CustomerMergerDTO();
        if (shoppingCartEntity.getCustomerEntity() != null) {
            customerMergerDTO = CustomerMergerDTO.builder()
                    .id(shoppingCartEntity.getCustomerEntity().getCustomerId())
                    .username(shoppingCartEntity.getCustomerEntity().getUsername())
                    .customerImageUrl(shoppingCartEntity.getCustomerEntity().getImage())
                    .build();
        }

        return ShoppingCartDTO.builder()
                .shoppingCartId(shoppingCartEntity.getShoppingCartId())
                .quantity(shoppingCartEntity.getQuantity())
                .grandTotal(shoppingCartEntity.getGrandTotal())
                .customerMergerDTO(shoppingCartEntity.getCustomerEntity() != null ? customerMergerDTO : null)
                .productMergerDTO(ProductMergerDTO.builder()
                        .productId(shoppingCartEntity.getProductEntity().getProductId())
                        .productName(shoppingCartEntity.getProductEntity().getProductName())
                        .build())
                .build();
    }

    protected ShoppingCartResponseDTO toResponseDTO(ShoppingCartEntity shoppingCartEntity) {
        CustomerMergerDTO customerMergerDTO = new CustomerMergerDTO();
        if (shoppingCartEntity.getCustomerEntity() != null) {
            customerMergerDTO = CustomerMergerDTO.builder()
                    .id(shoppingCartEntity.getCustomerEntity().getCustomerId())
                    .username(shoppingCartEntity.getCustomerEntity().getUsername())
                    .customerImageUrl(shoppingCartEntity.getCustomerEntity().getImage())
                    .build();
        }

        return ShoppingCartResponseDTO.builder()
                .shoppingCartId(shoppingCartEntity.getShoppingCartId())
                .quantity(shoppingCartEntity.getQuantity())
                .grandTotal(shoppingCartEntity.getGrandTotal())
                .customerMergerDTO(shoppingCartEntity.getCustomerEntity() != null ? customerMergerDTO : null)
                .productResponseMergedDTO(ProductResponseMergedDTO.builder()
                        .productId(Math.toIntExact(shoppingCartEntity.getProductEntity().getProductId()))
                        .price(shoppingCartEntity.getProductEntity().getSalePrice())
                        .productURL(shoppingCartEntity.getProductEntity().getProductImageUrl())
                        .productName(shoppingCartEntity.getProductEntity().getProductName())
                        .categoryMergerDTO(CategoryMergerDTO.builder()
                                .id(shoppingCartEntity.getProductEntity().getCategoryEntity().getCategoryId())
                                .categoryName(shoppingCartEntity.getProductEntity().getCategoryEntity().getCategoryName())
                                .build())
                        .build())
                .build();
    }

    protected List<ShoppingCartDTO> toDTOList(List<ShoppingCartEntity> shoppingCartEntityList) {
        return shoppingCartEntityList
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    protected List<ShoppingCartResponseDTO> toResponseDTOList(List<ShoppingCartEntity> shoppingCartEntityList) {
        return shoppingCartEntityList
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
