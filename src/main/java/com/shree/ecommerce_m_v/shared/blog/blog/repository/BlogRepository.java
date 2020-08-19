package com.shree.ecommerce_m_v.shared.blog.blog.repository;

import com.shree.ecommerce_m_v.shared.blog.blog.model.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity,Long> {
}
