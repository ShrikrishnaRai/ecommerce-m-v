package com.shree.ecommerce_m_v.shared.product.category.service.mapper;

import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryDTO;
import com.shree.ecommerce_m_v.shared.product.category.model.entity.CategoryEntity;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CategoryMapper {

    private  List<ProductEntity> productEntities = new ArrayList<>();

    public List<ProductEntity> getProductEntities(){
        return this.productEntities;
    }


    protected List<CategoryDTO> toDTOList(List<CategoryEntity> categoryEntityList) {
        return categoryEntityList.stream()
                .map(categoryEntity ->toDTO(categoryEntity))
                .collect(Collectors.toList());
    }

    protected CategoryDTO toDTO(CategoryEntity categoryEntity) {
        List<ProductMergerDTO> productMergerDTOs = new ArrayList<>();
        if (categoryEntity.getProductEntityList().size() != 0) {
            for (int i = 0; i < categoryEntity.getProductEntityList().size(); i++) {
                productMergerDTOs
                        .add(ProductMergerDTO.builder()
                        .productId(categoryEntity.getProductEntityList().get(i).getProductId())
                        .productName(categoryEntity.getProductEntityList().get(i).getProductName())
                        .build());
            }
        }
        return  CategoryDTO.builder()
                .categoryId(categoryEntity.getCategoryId())
                .categoryName(categoryEntity.getCategoryName())
                .parentId(categoryEntity.getParentId())
                .categoryImage(categoryEntity.getCategoryImage())
                .description(categoryEntity.getDescription())
                .productMergerDTOList(productMergerDTOs)
                .build();
    }

    protected CategoryEntity toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO.getProductMergerDTOList().size() != 0) {
            for (int i = 0; i < categoryDTO.getProductMergerDTOList().size(); i++) {
                productEntities.add(ProductEntity.builder()
                        .productId(categoryDTO.getProductMergerDTOList().get(i).getProductId())
                        .productName(categoryDTO.getProductMergerDTOList().get(i).getProductName())
                        .build());
            }
        }
        return CategoryEntity.builder()
                .categoryName(categoryDTO.getCategoryName())
                .parentId(categoryDTO.getParentId())
                .categoryImage(categoryDTO.getCategoryImage())
                .description(categoryDTO.getDescription())
                .productEntityList(categoryDTO.getProductMergerDTOList().size()!=0? productEntities:null)
                .build();

    }
}
