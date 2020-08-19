package com.shree.ecommerce_m_v.shared.product.product.service;

import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductDto;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Page<ProductDto> getProductList(int page, String sortBy, String orderBy, String filter);

    ProductDto getProductWithId(Long id);

    String saveProduct(ProductDto productDTO);

    ProductDto updateProduct(Long productId, ProductDto productDTO);

    Page<ProductDto> searchProductWithName(String productName, String sortBy, String orderBy);

    String deleteProductWithId(Long id);

    Page<ProductMergerDTO> getProductMergerDTOList(int page);

    List<ProductDto> getProductListByCategories(int categoryId);


}
