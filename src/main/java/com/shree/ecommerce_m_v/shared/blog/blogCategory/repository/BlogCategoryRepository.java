package com.shree.ecommerce_m_v.shared.blog.blogCategory.repository;

import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.entity.BlogCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategoryEntity,Long> {



}
