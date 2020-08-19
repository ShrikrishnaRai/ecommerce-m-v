package com.shree.ecommerce_m_v.shared.product.productImage.service;

import com.shree.ecommerce_m_v.shared.product.productImage.service.dto.ProductImageMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productImage.model.entity.ProductImageEntity;

import java.util.List;

public interface ProductImageService {

    void saveProductImage(ProductImageEntity productImageEntity);

    List<ProductImageMergerDTO> getProductImageMergerDTOs(List<ProductImageEntity> productImageEntities);

}
