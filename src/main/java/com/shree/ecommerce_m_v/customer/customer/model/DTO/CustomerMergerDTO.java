package com.shree.ecommerce_m_v.customer.customer.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerMergerDTO {
    private Long id;
    private String username;
    private String customerImageUrl;

}

