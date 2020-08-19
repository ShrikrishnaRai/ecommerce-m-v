package com.shree.ecommerce_m_v.customer.customerWallet.service.mapper;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.customerWallet.model.dto.CustomerWalletDTO;
import com.shree.ecommerce_m_v.customer.customerWallet.model.entity.CustomerWalletEntity;

/**
 * Mapper class to map entity to dto and vice-versa
 */

public abstract class CustomerWalletMapper {

    protected CustomerWalletEntity toEntity(CustomerWalletDTO customerWalletDTO) {

        CustomerEntity customerEntity = new CustomerEntity();
        if(customerWalletDTO.getCustomerMergerDTO() !=null){
            customerEntity=CustomerEntity
                    .builder()
                    .customerId(customerWalletDTO.getCustomerMergerDTO().getId())
                    .username(customerWalletDTO.getCustomerMergerDTO().getUsername())
                    .build();
        }

        return CustomerWalletEntity.builder()
                .currentAmount(customerWalletDTO.getCurrentAmount())
                .customerEntity(customerWalletDTO.getCustomerMergerDTO()!=null? customerEntity:null)
                .build();
    }

    protected CustomerWalletDTO toDTO(CustomerWalletEntity customerWalletEntity) {
        CustomerMergerDTO customerMergerDTO= new CustomerMergerDTO();
        if(customerWalletEntity.getCustomerEntity() !=null){
            customerMergerDTO=CustomerMergerDTO
                    .builder()
                    .id(customerWalletEntity.getCustomerEntity().getCustomerId())
                    .username(customerWalletEntity.getCustomerEntity().getUsername())
                    .build();
        }

        return CustomerWalletDTO
                .builder()
                .customerWalletId(customerWalletEntity.getCustomerWalletId())
                .currentAmount(customerWalletEntity.getCurrentAmount())
                .customerMergerDTO(customerMergerDTO)
                .build();
    }
}
