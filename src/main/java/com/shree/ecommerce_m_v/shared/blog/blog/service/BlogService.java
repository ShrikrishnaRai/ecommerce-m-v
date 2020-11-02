package com.shree.ecommerce_m_v.shared.blog.blog.service;

import com.shree.ecommerce_m_v.shared.blog.blog.model.DTO.BlogDTO;
import com.shree.ecommerce_m_v.shared.blog.blog.model.entity.BlogEntity;
import com.shree.ecommerce_m_v.shared.blog.blog.repository.BlogRepository;
import com.shree.ecommerce_m_v.shared.blog.blog.service.mapper.BlogMapper;
import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.entity.BlogCategoryEntity;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BlogService extends BlogMapper  {

    @Autowired
    private BlogRepository blogRepository;

    public Page<BlogDTO> getAllBlogs(int page, String sortBy, String orderBy) {

        Pageable pageable= PageRequest.of(page,20, Sort.by(Sort.Direction.valueOf(orderBy),sortBy));
        Page<BlogEntity> blogEntities=blogRepository.findAll(pageable);
        return blogEntities.map(this::toDTO);
    }

    public String saveBlog(BlogDTO blogDTO) {
        blogRepository.save(toEntity(blogDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    public String deleteBlog(Long blogId) {
        blogRepository.deleteById(blogId);
        return ResponseValue.DELETE_SUCCESS;
    }

    public BlogDTO getBlogById(Long blogId) {
        return toDTO(blogRepository.getOne(blogId));
    }

    public BlogDTO updateBlog(Long blogId, BlogDTO blogDTO) {

        BlogEntity blogEntity=blogRepository.getOne(blogId);
        blogEntity.setContent(blogDTO.getContent());
        blogEntity.setStatus(blogDTO.getStatus());
        if(blogDTO.getBlogCategoryMergerDTO()!=null){
            blogEntity.setBlogCategoryEntity(BlogCategoryEntity.builder()
                    .blogCategoryId(blogDTO.getBlogCategoryMergerDTO().getBlogCategoryID())
                    .blogCategory(blogDTO.getBlogCategoryMergerDTO().getBlogCategory())
                    .build());
        }
        blogRepository.save(blogEntity);
        return toDTO(blogEntity);
    }
}
