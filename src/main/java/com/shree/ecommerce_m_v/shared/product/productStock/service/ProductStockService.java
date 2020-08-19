package com.shree.ecommerce_m_v.shared.product.productStock.service;

import com.shree.ecommerce_m_v.shared.product.productStock.service.dto.ProductStockDTO;

import java.util.List;

public interface ProductStockService {
    List<ProductStockDTO> getProductsStockLists();

    ProductStockDTO getProductStockById(final Long productStockId);

    String deleteProductStockWithId(Long id);

    ProductStockDTO updateProduct(Long productStockId, ProductStockDTO productStockDTO);

    String saveProduct(ProductStockDTO productStockDTO);
}
