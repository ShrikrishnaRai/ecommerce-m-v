package com.shree.ecommerce_m_v.shared.product.brand.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileDTO {
    private String base64;
    private String name;
    private String type;
    private String lastModified;
}
