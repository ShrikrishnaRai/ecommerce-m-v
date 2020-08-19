package com.shree.ecommerce_m_v.shared.blog.blogCategory.model.entity;

import com.shree.ecommerce_m_v.shared.blog.blog.model.entity.BlogEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="BlogCategoryEntity")
@Table(name="blog_category")
public class BlogCategoryEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogCategoryId;
    private String blogCategory;
    private Status status;

    @OneToMany(mappedBy = "blogCategoryEntity")
    private List<BlogEntity> blogEntities= new ArrayList<>();
}
