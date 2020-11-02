package com.shree.ecommerce_m_v.shared.product.productStock.service;

import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.productStock.service.dto.ProductStockDTO;
import com.shree.ecommerce_m_v.shared.product.productStock.model.entity.ProductStockEntity;
import com.shree.ecommerce_m_v.shared.product.productStock.repository.ProductStockRepository;
import com.shree.ecommerce_m_v.shared.product.productStock.service.mapper.ProductStockMapper;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductStockService extends ProductStockMapper {

    @Autowired
    private ProductStockRepository productStockRepository;

    public List<ProductStockDTO> getProductsStockLists() {
        return productStockRepository.findAll().stream()
                .map(productStockEntity -> toDTO(productStockEntity))
                .collect(Collectors.toList());
    }

    public ProductStockDTO getProductStockById(Long productStockId) {
        return toDTO(productStockRepository.getOne(productStockId));
    }

    public String deleteProductStockWithId(Long id) {
        productStockRepository.deleteById(id);
        return ResponseValue.DELETE_SUCCESS;
    }

    public ProductStockDTO updateProduct(Long productStockId, ProductStockDTO productStockDTO) {
        ProductStockEntity productStockEntity= productStockRepository.getOne(productStockId);
        if (productStockDTO.getProductMergerDTO() != null) {
            productStockEntity.setProductEntity(ProductEntity.builder()
                    .productId(productStockDTO.getProductMergerDTO().getProductId())
                    .productName(productStockDTO.getProductMergerDTO().getProductName())
                    .build());
        }

        productStockEntity.setTotalQuantity(productStockDTO.getTotalQuantity());
        productStockEntity.setRemainingQuantity(productStockDTO.getRemainingQuantity());
        productStockRepository.saveAndFlush(productStockEntity);
        return toDTO(productStockEntity);
    }

    public String saveProduct(ProductStockDTO productStockDTO) {
        productStockRepository.save(toEntity(productStockDTO));
        return ResponseValue.SAVE_SUCCESS;
    }
}
