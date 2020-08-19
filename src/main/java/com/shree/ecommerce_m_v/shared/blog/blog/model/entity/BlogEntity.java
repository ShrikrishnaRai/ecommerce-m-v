package com.shree.ecommerce_m_v.shared.blog.blog.model.entity;

import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.entity.BlogCategoryEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name="BlogEntity")
@Table(name="blog")
public class BlogEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;
    private String content;
    private Status status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="blog_category_id_fk")
    private BlogCategoryEntity blogCategoryEntity;


}
