package com.shree.ecommerce_m_v.shared.blog.blogCategory.model.DTO;

import com.shree.ecommerce_m_v.shared.blog.blogCategory.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogCategoryDTO {

    private Long blogCategoryId;
    private String blogCategory;
    private Status status;


}
