package com.shree.ecommerce_m_v.customer.complain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplainMergerDTO {

    private Long complainId;
    private String topic;
}
