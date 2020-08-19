package com.shree.ecommerce_m_v.shared.blog.blogCategory.service;

import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.DTO.BlogCategoryDTO;
import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.entity.BlogCategoryEntity;
import com.shree.ecommerce_m_v.shared.blog.blogCategory.repository.BlogCategoryRepository;
import com.shree.ecommerce_m_v.shared.blog.blogCategory.service.mapper.BlogCategoryMapper;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BlogCategoryServiceImpl extends BlogCategoryMapper implements BlogCategoryService {


    @Autowired
    private BlogCategoryRepository blogCategoryRepository;


    @Override
    public Page<BlogCategoryDTO> getAllBlogCategory(int page, String sortBy, String orderBy) {

        Pageable pageable= PageRequest.of(page,20, Sort.by(Sort.Direction.valueOf(orderBy),sortBy));
        Page<BlogCategoryEntity> blogCategories=blogCategoryRepository.findAll(pageable);
        return blogCategories.map(this::toDTO);
    }

    @Override
    public BlogCategoryDTO getBlogCategoryById(Long blogCategoryId) {
        return toDTO(blogCategoryRepository.getOne(blogCategoryId));
    }

    @Override
    public String saveBlogCategory(BlogCategoryDTO blogCategoryDTO) {
        blogCategoryRepository.save(toEntity(blogCategoryDTO));
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public String deleteBlogCategory(Long blogCategoryId) {
        blogCategoryRepository.deleteById(blogCategoryId);
        return ResponseValue.DELETE_SUCCESS;
    }

    @Override
    public BlogCategoryDTO updateBlogCategory(Long blogCategoryId, BlogCategoryDTO blogCategoryDTO) {

        BlogCategoryEntity blogCategoryEntity= blogCategoryRepository.getOne(blogCategoryId);
        blogCategoryEntity.setBlogCategory(blogCategoryDTO.getBlogCategory());
        blogCategoryEntity.setStatus(blogCategoryDTO.getStatus());
        blogCategoryRepository.saveAndFlush(blogCategoryEntity);
    return toDTO(blogCategoryEntity);
    }
}
