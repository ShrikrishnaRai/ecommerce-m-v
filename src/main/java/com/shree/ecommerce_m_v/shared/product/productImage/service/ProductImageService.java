package com.shree.ecommerce_m_v.shared.product.productImage.service;

import com.shree.ecommerce_m_v.shared.product.productImage.service.dto.ProductImageMergerDTO;
import com.shree.ecommerce_m_v.shared.product.productImage.model.entity.ProductImageEntity;
import com.shree.ecommerce_m_v.shared.product.productImage.repository.ProductImageRepository;
import com.shree.ecommerce_m_v.shared.product.productImage.service.mapper.ProductImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImageService extends ProductImageMapper  {

    @Autowired
    private ProductImageRepository productImageRepository;

    public void saveProductImage(ProductImageEntity productImageEntity) {
       productImageRepository.save(productImageEntity);
    }

    public List<ProductImageMergerDTO> getProductImageMergerDTOs(List<ProductImageEntity> productImageEntities) {
        return productImageEntities.stream()
                .map(productImageEntity -> toDTO(productImageEntity))
                .collect(Collectors.toList());
    }


}
