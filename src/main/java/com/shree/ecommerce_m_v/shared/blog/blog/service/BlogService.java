package com.shree.ecommerce_m_v.shared.blog.blog.service;

import com.shree.ecommerce_m_v.shared.blog.blog.model.DTO.BlogDTO;
import org.springframework.data.domain.Page;

public interface BlogService {

    Page<BlogDTO> getAllBlogs(int page, String sortBy, String orderBy);

    String saveBlog(BlogDTO blogDTO);

    String deleteBlog(final Long blogId);

    BlogDTO getBlogById(final Long blogId);

    BlogDTO updateBlog(final Long blogId,BlogDTO blogDTO);
}
