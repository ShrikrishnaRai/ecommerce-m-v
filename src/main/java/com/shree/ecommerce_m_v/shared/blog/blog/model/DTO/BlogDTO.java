package com.shree.ecommerce_m_v.shared.blog.blog.model.DTO;

import com.shree.ecommerce_m_v.shared.blog.blog.model.entity.Status;
import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.DTO.BlogCategoryMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {

    private Long blogId;
    private String content;
    private Status status;

    private BlogCategoryMergerDTO blogCategoryMergerDTO;
}
