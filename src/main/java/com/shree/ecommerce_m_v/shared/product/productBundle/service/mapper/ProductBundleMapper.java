package com.shree.ecommerce_m_v.shared.product.productBundle.service.mapper;

import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;
import com.shree.ecommerce_m_v.shared.product.productBundle.service.dto.ProductBundleDTO;
import com.shree.ecommerce_m_v.shared.product.productBundle.model.entity.ProductBundleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ProductBundleMapper {

    private List<ProductEntity> productEntities= new ArrayList<>();

    public List<ProductEntity> getProductEntities(){return this.productEntities;}

    protected List<ProductBundleDTO> toDtoList(List<ProductBundleEntity> productBundleEntityList) {
        return productBundleEntityList
                .stream()
                .map(productBundleEntity -> toDTO(productBundleEntity))
                .collect(Collectors.toList());
    }

    protected ProductBundleEntity toEntity(ProductBundleDTO productBundleDTO) {
        if(productBundleDTO.getProductMergerDTOList().size() !=0){
            for (int i=0;i<productBundleDTO.getProductMergerDTOList().size();i++){
                productEntities.add(ProductEntity.builder()
                        .productId(productBundleDTO.getProductMergerDTOList().get(i).getProductId())
                        .productName(productBundleDTO.getProductMergerDTOList().get(i).getProductName())
                        .build());
            }
        }
        return ProductBundleEntity.builder()
                .productBundleCode(productBundleDTO.getProductBundleCode())
                .productBundleName(productBundleDTO.getProductBundleName())
                .productBundlePrice(productBundleDTO.getProductBundlePrice())
                .status(productBundleDTO.getStatus())
                .tag(productBundleDTO.getTag())
                .isTaxable(productBundleDTO.isTaxable())
                .taxAmount(productBundleDTO.getTaxAmount())
                .totalQuantity(productBundleDTO.getTotalQuantity())
                .remainingQuantity(productBundleDTO.getRemainingQuantity())
                .productEntityList(productBundleDTO.getProductMergerDTOList().size()!=0? productEntities:null)
                .build();
    }

    protected ProductBundleDTO toDTO(ProductBundleEntity productBundleEntity) {
        List<ProductMergerDTO> productMergerDTOS = new ArrayList<>();
        if(productBundleEntity.getProductEntityList().size() !=0){
            for (int i=0;i<productBundleEntity.getProductEntityList().size();i++){
                productMergerDTOS.add(ProductMergerDTO.builder()
                        .productId(productBundleEntity.getProductEntityList().get(i).getProductId())
                        .productName(productBundleEntity.getProductEntityList().get(i).getProductName())
                        .build());
            }
        }

        return ProductBundleDTO.builder()
                .productBundleId(productBundleEntity.getProductBundleId())
                .productBundleCode(productBundleEntity.getProductBundleCode())
                .productBundleName(productBundleEntity.getProductBundleName())
                .productBundlePrice(productBundleEntity.getProductBundlePrice())
                .status(productBundleEntity.getStatus())
                .tag(productBundleEntity.getTag())
                .isTaxable(productBundleEntity.isTaxable())
                .taxAmount(productBundleEntity.getTaxAmount())
                .totalQuantity(productBundleEntity.getTotalQuantity())
                .remainingQuantity(productBundleEntity.getRemainingQuantity())
                .productMergerDTOList(productMergerDTOS)
                .build();
    }
}
