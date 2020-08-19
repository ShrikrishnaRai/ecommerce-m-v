package com.shree.ecommerce_m_v.shared.product.category.service.dto;

import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private long categoryId;
    private String categoryName;
    private String parentId;
    private String categoryImage;
    private String description;
    private List<ProductMergerDTO> productMergerDTOList = new ArrayList<>();


}
