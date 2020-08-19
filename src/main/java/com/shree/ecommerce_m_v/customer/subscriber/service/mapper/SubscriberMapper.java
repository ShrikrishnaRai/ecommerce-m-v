package com.shree.ecommerce_m_v.customer.subscriber.service.mapper;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.subscriber.model.dto.SubscriberDTO;
import com.shree.ecommerce_m_v.customer.subscriber.model.entity.SubscriberEntity;

public abstract class SubscriberMapper {

    protected SubscriberEntity toEntity(SubscriberDTO subscriberDTO){
        CustomerEntity customer = new CustomerEntity();
        if(subscriberDTO.getCustomerMergerDTO() !=null){
            customer=CustomerEntity.builder()
                    .customerId(subscriberDTO.getCustomerMergerDTO().getId())
                    .username(subscriberDTO.getCustomerMergerDTO().getUsername())
                    .build();
        }
        return SubscriberEntity.builder()
                .subscriberId(subscriberDTO.getSubscriberId())
                .email(subscriberDTO.getEmail())
                .customerEntity(subscriberDTO.getCustomerMergerDTO()!=null? customer:null)
                .build();
    }

    protected SubscriberDTO toDTO(SubscriberEntity subscriberEntity){
        CustomerMergerDTO customerMergerDTO = new CustomerMergerDTO();
        if(subscriberEntity.getCustomerEntity() !=null){
            customerMergerDTO=CustomerMergerDTO.builder()
                    .id(subscriberEntity.getCustomerEntity().getCustomerId())
                    .username(subscriberEntity.getCustomerEntity().getUsername())
                    .build();
        }
        return SubscriberDTO.builder()
                .subscriberId(subscriberEntity.getSubscriberId())
                .email(subscriberEntity.getEmail())
                .customerMergerDTO(customerMergerDTO)
                .build();

    }
}
