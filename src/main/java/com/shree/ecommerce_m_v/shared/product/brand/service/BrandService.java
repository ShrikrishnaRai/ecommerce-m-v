package com.shree.ecommerce_m_v.shared.product.brand.service;

import com.shree.ecommerce_m_v.shared.product.brand.service.dto.BrandDTO;
import com.shree.ecommerce_m_v.shared.product.brand.service.dto.BrandRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {
    Page<BrandDTO> getBrandList(int page,String sortBy,String orderBy);

    String saveBrand(BrandRequestDTO brandDTO);

    BrandDTO getBrandWithId(Long id);

    BrandDTO updateBrand(Long brandId, BrandDTO brandDTO);

    List<BrandDTO> searchBrandWithName(String brandName);

    String deleteBrandWithId(Long id);

}
