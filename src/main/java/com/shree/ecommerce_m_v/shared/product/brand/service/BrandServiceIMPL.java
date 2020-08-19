package com.shree.ecommerce_m_v.shared.product.brand.service;

import com.shree.ecommerce_m_v.shared.product.brand.service.dto.BrandDTO;
import com.shree.ecommerce_m_v.shared.product.brand.service.dto.BrandRequestDTO;
import com.shree.ecommerce_m_v.shared.product.brand.model.entity.BrandEntity;
import com.shree.ecommerce_m_v.shared.product.brand.repository.BrandRepository;
import com.shree.ecommerce_m_v.shared.product.brand.service.mapper.BrandMapper;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.repository.ProductRepository;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceIMPL extends BrandMapper implements BrandService {

    private final BrandRepository brandRepository;

    private final ProductRepository productRepository;

    @Autowired
    public BrandServiceIMPL(final BrandRepository brandRepository, final ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Page<BrandDTO> getBrandList(int page, String sortBy, String orderBy) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.valueOf(orderBy), sortBy));
        Page<BrandEntity> result = brandRepository.findAll(pageable);
        return result.map(brandEntity -> toDTO(brandEntity));
    }

    @Override
    public String saveBrand(BrandRequestDTO brandDTO) {
        BrandEntity brandEntity = toEntity(brandDTO);
        for (ProductMergerDTO productMergerDTO : brandDTO.getProductMergerDTOList()) {
            if (productRepository.existsById(productMergerDTO.getProductId())) {
                brandEntity.addBrandToProduct(productRepository.getOne(productMergerDTO.getProductId()));
            }
        }
        brandRepository.saveAndFlush(brandEntity);
        return "Brand with brand name " + brandEntity.getBrandName() + " " + ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public BrandDTO getBrandWithId(Long id) {
        return toDTO(brandRepository.getOne(id));
    }

    @Override
    public BrandDTO updateBrand(Long brandId, BrandDTO brandDTO) {
        BrandEntity brandEntity = brandRepository.getOne(brandId);
        for (ProductMergerDTO productMergerDTO : brandDTO.getProductMergerDTOList()) {
            if (productRepository.existsById(productMergerDTO.getProductId())) {
                brandEntity.addBrandToProduct(productRepository.getOne(productMergerDTO.getProductId()));
            }
        }

        brandEntity.setBrandName(brandDTO.getBrandName());
        brandEntity.setBrandImage(brandDTO.getBrandImage());
        brandEntity.setCompanyName(brandDTO.getCompanyName());
        brandEntity.setSlug(brandDTO.getSlug());
        brandEntity.setStatus(brandDTO.getStatus());

        brandRepository.saveAndFlush(brandEntity);
        return toDTO(brandEntity);
    }

    @Override
    public List<BrandDTO> searchBrandWithName(String brandName) {
        return toBrandDTOList(brandRepository.getBrandWithName(brandName));
    }

    @Override
    public String deleteBrandWithId(Long id) {
        BrandEntity brandEntity = brandRepository.getOne(id);
        if (brandEntity.getProductEntityList() != null) {
            for (int i = brandEntity.getProductEntityList().size() - 1; i >= 0; --i) {
                brandEntity.removeBrandFromProduct(brandEntity.getProductEntityList().get(i));
            }
        }
        brandRepository.deleteById(id);
        return ResponseValue.DELETE_SUCCESS;
    }

}



