package com.shree.ecommerce_m_v.shared.product.productStock.service.mapper;

import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.productStock.service.dto.ProductStockDTO;
import com.shree.ecommerce_m_v.shared.product.productStock.model.entity.ProductStockEntity;

public abstract class ProductStockMapper {

    protected ProductStockEntity toEntity(ProductStockDTO productStockDTO) {
        ProductEntity productEntity = new ProductEntity();
        if (productStockDTO.getProductMergerDTO() != null) {
            productEntity= ProductEntity.builder()
                    .productId(productStockDTO.getProductMergerDTO().getProductId())
                    .productName(productStockDTO.getProductMergerDTO().getProductName())
                    .build();
        }

        return ProductStockEntity.builder()
                .totalQuantity(productStockDTO.getTotalQuantity())
                .remainingQuantity(productStockDTO.getRemainingQuantity())
                .productEntity(productStockDTO.getProductMergerDTO()!=null? productEntity:null)
                .build();

    }


    protected ProductStockDTO toDTO(ProductStockEntity productStockEntity) {
        ProductMergerDTO productMergerDTO = new ProductMergerDTO();
        if (productStockEntity.getProductEntity() != null) {
            productMergerDTO= ProductMergerDTO.builder()
                    .productId(productStockEntity.getProductEntity().getProductId())
                    .productName(productStockEntity.getProductEntity().getProductName())
                    .build();
        }

        return ProductStockDTO.builder()
                .productStockId(productStockEntity.getProductStockId())
                .totalQuantity(productStockEntity.getTotalQuantity())
                .remainingQuantity(productStockEntity.getRemainingQuantity())
                .productMergerDTO(productMergerDTO)
                .build();

    }


}
