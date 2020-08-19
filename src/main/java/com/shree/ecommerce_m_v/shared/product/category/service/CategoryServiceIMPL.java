package com.shree.ecommerce_m_v.shared.product.category.service;

import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryDTO;
import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryIdAndName;
import com.shree.ecommerce_m_v.shared.product.category.model.entity.CategoryEntity;
import com.shree.ecommerce_m_v.shared.product.category.repository.CategoryRepository;
import com.shree.ecommerce_m_v.shared.product.category.service.mapper.CategoryMapper;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.repository.ProductRepository;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceIMPL extends CategoryMapper implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String saveCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = toEntity(categoryDTO);
        for (ProductMergerDTO productMergerDTO : categoryDTO.getProductMergerDTOList()) {
            if (productRepository.existsById(productMergerDTO.getProductId())) {
                categoryEntity.addCategoryToProduct(productRepository.getOne(productMergerDTO.getProductId()));
            }
        }
        categoryRepository.saveAndFlush(categoryEntity);
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public CategoryDTO getCategoryWithID(Long id) {
        return toDTO(categoryRepository.getOne(id));
    }

    @Override
    public Page<CategoryDTO> getCategoryList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<CategoryEntity> result = categoryRepository.findAll(pageable);
        return result.map(categoryEntity -> toDTO(categoryEntity));
    }

    @Override
    public String deleteCategoryWithId(Long id) {
        CategoryEntity categoryEntity = categoryRepository.getOne(id);
        if (categoryEntity.getProductEntityList() != null) {
            for (int i = categoryEntity.getProductEntityList().size() - 1; i >= 0; --i) {
                categoryEntity.removeCategoryFromProduct(categoryEntity.getProductEntityList().get(i));
            }
        }
        categoryRepository.deleteById(id);
        return ResponseValue.DELETE_SUCCESS;
    }

    @Override
    public Page<CategoryDTO> getCategoryWithName(String categoryName) {

        Pageable pageable = PageRequest.of(0, 10);
        Page<CategoryEntity> result = categoryRepository.getCategoryWithName(categoryName, pageable);

        return result.map(categoryEntity -> toDTO(categoryEntity));
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryRepository.getOne(categoryId);
        if (categoryDTO.getProductMergerDTOList() != null) {
            for (ProductMergerDTO productMergerDTO : categoryDTO.getProductMergerDTOList()) {
                if (productRepository.existsById(productMergerDTO.getProductId())) {
                    categoryEntity.addCategoryToProduct(productRepository.getOne(productMergerDTO.getProductId()));
                }
            }
        }
        categoryEntity.setCategoryName(categoryDTO.getCategoryName());
        categoryEntity.setCategoryImage(categoryDTO.getCategoryImage());
        categoryEntity.setParentId(categoryDTO.getParentId());
        categoryEntity.setDescription(categoryDTO.getDescription());
        categoryRepository.saveAndFlush(categoryEntity);
        return toDTO(categoryEntity);
    }

    @Override
    public List<CategoryIdAndName> getCategoryNameAndId() {
        return categoryRepository.getAllBy();
    }

}
