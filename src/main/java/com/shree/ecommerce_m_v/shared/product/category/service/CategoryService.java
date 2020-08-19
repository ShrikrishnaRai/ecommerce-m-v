package com.shree.ecommerce_m_v.shared.product.category.service;

import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryDTO;
import com.shree.ecommerce_m_v.shared.product.category.service.dto.CategoryIdAndName;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    String saveCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryWithID(Long id);

    Page<CategoryDTO> getCategoryList(int page);

    String deleteCategoryWithId(Long id);

    Page<CategoryDTO> getCategoryWithName(String categoryName);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);

    List<CategoryIdAndName> getCategoryNameAndId();

}
