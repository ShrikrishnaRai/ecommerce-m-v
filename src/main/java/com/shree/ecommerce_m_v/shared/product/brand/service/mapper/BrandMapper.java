package com.shree.ecommerce_m_v.shared.product.brand.service.mapper;

import com.shree.ecommerce_m_v.shared.product.brand.service.dto.BrandDTO;
import com.shree.ecommerce_m_v.shared.product.brand.service.dto.BrandRequestDTO;
import com.shree.ecommerce_m_v.shared.product.brand.model.entity.BrandEntity;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BrandMapper {

    private final List<ProductEntity> productEntityList = new ArrayList<>();

    public final List<ProductEntity> getProductEntityList(){
        return this.productEntityList;
    }

    protected final BrandDTO toDTO(BrandEntity brandEntity) {
        List<ProductMergerDTO> productMergerDTOList = new ArrayList<>();
        if (brandEntity.getProductEntityList().size() != 0) {
            for (int i = 0; i < brandEntity.getProductEntityList().size(); i++) {
                productMergerDTOList.add(ProductMergerDTO.builder()
                        .productId(brandEntity.getProductEntityList().get(i).getProductId())
                        .productName(brandEntity.getProductEntityList().get(i).getProductName())
                        .build());
            }
        }
        return BrandDTO.builder()
                .brandId(brandEntity.getBrandId())
                .brandName(brandEntity.getBrandName())
                .companyName(brandEntity.getCompanyName())
                .brandImage(brandEntity.getBrandImage())
                .status(brandEntity.getStatus())
                .slug(brandEntity.getSlug())
                .productMergerDTOList(productMergerDTOList)
                .build();

    }

    protected final BrandEntity toEntity(BrandRequestDTO brandDTO) {
        if (brandDTO.getProductMergerDTOList().size() != 0) {
            for (int i = 0; i < brandDTO.getProductMergerDTOList().size(); i++) {
                productEntityList.add(ProductEntity.builder()
                        .productId(brandDTO.getProductMergerDTOList().get(i).getProductId())
                        .productName(brandDTO.getProductMergerDTOList().get(i).getProductName())
                        .build());
            }
        }
        return BrandEntity.builder()
                .brandName(brandDTO.getBrandName())
                .brandImage(brandDTO.getBrandImage())
                .companyName(brandDTO.getCompanyName())
                .slug(brandDTO.getSlug())
                .status(brandDTO.getStatus())
                .productEntityList(brandDTO.getProductMergerDTOList().size()!=0? productEntityList:null)
                .build();
    }

    protected final List<BrandDTO> toBrandDTOList(List<BrandEntity> brandEntityList) {
        return brandEntityList.stream()
                .map(brandEntity -> toDTO(brandEntity))
                .collect(Collectors.toList());
    }
}
