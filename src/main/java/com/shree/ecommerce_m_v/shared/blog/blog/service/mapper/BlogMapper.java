package com.shree.ecommerce_m_v.shared.blog.blog.service.mapper;

import com.shree.ecommerce_m_v.shared.blog.blog.model.DTO.BlogDTO;
import com.shree.ecommerce_m_v.shared.blog.blog.model.entity.BlogEntity;
import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.DTO.BlogCategoryMergerDTO;
import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.entity.BlogCategoryEntity;

public abstract class BlogMapper {

    protected BlogEntity toEntity(BlogDTO blogDTO){


        BlogCategoryEntity blogCategoryEntity= new BlogCategoryEntity();
        if(blogDTO.getBlogCategoryMergerDTO()!=null){
            blogCategoryEntity= BlogCategoryEntity.builder()
                    .blogCategoryId(blogDTO.getBlogCategoryMergerDTO().getBlogCategoryID())
                    .blogCategory(blogDTO.getBlogCategoryMergerDTO().getBlogCategory())
                    .build();
        }

        return BlogEntity.builder()
                .content(blogDTO.getContent())
                .status(blogDTO.getStatus())
                .blogCategoryEntity(blogDTO.getBlogCategoryMergerDTO()!=null?blogCategoryEntity:null)
                .build();
    }


    protected BlogDTO toDTO(BlogEntity blogEntity){


        BlogCategoryMergerDTO blogCategoryMergerDTO= new BlogCategoryMergerDTO();
        if(blogEntity.getBlogCategoryEntity()!=null){
            blogCategoryMergerDTO= BlogCategoryMergerDTO.builder()
                    .blogCategoryID(blogEntity.getBlogCategoryEntity().getBlogCategoryId())
                    .blogCategory(blogEntity.getBlogCategoryEntity().getBlogCategory())
                    .build();
        }

        return BlogDTO.builder()
                .content(blogEntity.getContent())
                .status(blogEntity.getStatus())
                .blogCategoryMergerDTO(blogEntity.getBlogCategoryEntity()!=null?blogCategoryMergerDTO:null)
                .build();
    }
}
