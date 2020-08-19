package com.shree.ecommerce_m_v.shared.product.productBundle.service;

import com.shree.ecommerce_m_v.shared.product.productBundle.service.dto.ProductBundleDTO;
import org.springframework.data.domain.Page;

public interface ProductBundleService {
    String saveProductBundle(ProductBundleDTO productBundleDTO);

    Page<ProductBundleDTO> getProductBundleList(int page,String sortBy,String orderBy);

    ProductBundleDTO getProductBundleById(final Long productBundleId);

    String deleteProductBundleWithId(Long id);

    ProductBundleDTO updateProductBundle(long id, ProductBundleDTO productBundleDTO);
}
