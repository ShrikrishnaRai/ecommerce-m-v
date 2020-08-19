package com.shree.ecommerce_m_v.shared.product.brand.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandRequestDTO implements Serializable {
    private static final long serialVersionUID = -3248612934171987864L;

    private String brandName;
    private String companyName;
    @JsonIgnore
    private String brandImage;
    private String status;
    private String slug;
    private FileDTO fileDTO;
    private List<ProductMergerDTO> productMergerDTOList = new ArrayList<>();

}
