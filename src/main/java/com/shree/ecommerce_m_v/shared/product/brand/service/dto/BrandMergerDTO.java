package com.shree.ecommerce_m_v.shared.product.brand.service.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BrandMergerDTO implements Serializable {
    private static final long serialVersionUID = -6199718893379810483L;
    private Long brandId;
    private String brandName;


}
