package com.shree.ecommerce_m_v.shared.product.brand.service.dto;

import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandDTO implements Serializable {
    private static final long serialVersionUID = -3248612934171987864L;

    private long brandId;
    private String brandName;
    private String companyName;
    private String brandImage;
    private String status;
    private String slug;
    private FileDTO fileDTO;
    private List<ProductMergerDTO> productMergerDTOList = new ArrayList<>();

}
