package com.shree.ecommerce_m_v.vendor.service.serviceCategory.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCategoryMergerDTO {

    private Long serviceCategoryId;
    private String serviceCategoryName;
}
