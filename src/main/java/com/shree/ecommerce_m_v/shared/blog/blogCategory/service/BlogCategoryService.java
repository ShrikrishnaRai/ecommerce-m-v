package com.shree.ecommerce_m_v.shared.blog.blogCategory.service;

import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.DTO.BlogCategoryDTO;
import org.springframework.data.domain.Page;

public interface BlogCategoryService {


    Page<BlogCategoryDTO> getAllBlogCategory(int page, String sortBy, String orderBy);

    BlogCategoryDTO getBlogCategoryById(final Long blogCategoryId);

    String saveBlogCategory(final BlogCategoryDTO blogCategoryDTO);

    String deleteBlogCategory(final Long blogCategoryId);

    BlogCategoryDTO updateBlogCategory(final Long blogCategoryId,final BlogCategoryDTO blogCategoryDTO);
}
