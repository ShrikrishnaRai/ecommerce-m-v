package com.shree.ecommerce_m_v.customer.subscriber.model.dto;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberDTO {

    private Long subscriberId;
    private String email;
    private CustomerMergerDTO customerMergerDTO;


}
