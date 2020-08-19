package com.shree.ecommerce_m_v.shared.product.product.service.dto;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductMergerDTO {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;


}
