package com.shree.ecommerce_m_v.customer.complain.service.dto;

import com.shree.ecommerce_m_v.customer.complain.model.Status;
import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplainDTO {

    private long complainId;
    private String topic;
    private String message;
    private String reply;
    private Status status;

    private CustomerMergerDTO customerMergerDTO;


}
