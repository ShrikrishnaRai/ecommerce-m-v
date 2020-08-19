package com.shree.ecommerce_m_v.shared.blog.blogCategory.service.mapper;

import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.DTO.BlogCategoryDTO;
import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.entity.BlogCategoryEntity;

public  abstract class BlogCategoryMapper {

    protected BlogCategoryEntity toEntity(BlogCategoryDTO blogCategoryDTO){


        return BlogCategoryEntity.builder()
                .blogCategory(blogCategoryDTO.getBlogCategory())
                .status(blogCategoryDTO.getStatus())
                .build();
    }

    protected BlogCategoryDTO toDTO(BlogCategoryEntity blogCategoryEntity){
        return BlogCategoryDTO.builder()
                .blogCategoryId(blogCategoryEntity.getBlogCategoryId())
                .blogCategory(blogCategoryEntity.getBlogCategory())
                .status(blogCategoryEntity.getStatus())
                .build();
    }
}
