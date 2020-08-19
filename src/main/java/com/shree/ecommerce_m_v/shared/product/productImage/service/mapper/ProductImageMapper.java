package com.shree.ecommerce_m_v.shared.product.productImage.service.mapper;

import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.productImage.service.dto.ProductImageDTO;
import com.shree.ecommerce_m_v.shared.product.productImage.service.dto.ProductImageMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productImage.model.entity.ProductImageEntity;

public abstract class ProductImageMapper {

    protected ProductImageEntity toEntity(ProductImageDTO productImageDTO){

        ProductEntity productEntity= new ProductEntity();
        if(productImageDTO.getProductMergerDTO()!=null){
            productEntity=ProductEntity.builder()
                    .productId(productImageDTO.getProductMergerDTO().getProductId())
                    .productName(productImageDTO.getProductMergerDTO().getProductName())
                    .build();
        }
        return ProductImageEntity.builder()
                .productImageId(productImageDTO.getProductImageId())
                .productImage(productImageDTO.getProductImage())
                .productEntity(productImageDTO.getProductMergerDTO()!=null? productEntity:null)
                .build();
    }

    protected ProductImageMergerDTO toDTO(ProductImageEntity productImageEntity){
        return ProductImageMergerDTO.builder()
                .productImageId(productImageEntity.getProductImageId())
                .productImage(productImageEntity.getProductImage())
                .build();
    }


}
