package com.shree.ecommerce_m_v.shared.blog.blogCategory.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogCategoryMergerDTO {

    private Long blogCategoryID;
    private String blogCategory;
}
