package com.shree.ecommerce_m_v.customer.complain.service.mapper;

import com.shree.ecommerce_m_v.customer.complain.service.dto.ComplainDTO;
import com.shree.ecommerce_m_v.customer.complain.model.entity.ComplainEntity;
import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ComplainMapper {

    protected ComplainEntity toEntity(ComplainDTO complainDTO) {

        CustomerEntity customerEntity=new CustomerEntity();
        if(complainDTO.getCustomerMergerDTO() !=null){
            customerEntity=CustomerEntity.
                    builder()
                    .customerId(complainDTO.getCustomerMergerDTO().getId())
                    .username(complainDTO.getCustomerMergerDTO().getUsername())
                    .build();
        }
        return ComplainEntity.builder()
                .topic(complainDTO.getTopic())
                .message(complainDTO.getMessage())
                .reply(complainDTO.getReply())
                .status(complainDTO.getStatus())
                .customerEntity(complainDTO.getCustomerMergerDTO()!=null? customerEntity: null)
                .build();
    }

    protected ComplainDTO toDTO(ComplainEntity complainEntity) {
        CustomerMergerDTO customerMergerDTO= new CustomerMergerDTO();
        if(complainEntity.getCustomerEntity()!=null){
            customerMergerDTO=CustomerMergerDTO.builder()
                    .id(complainEntity.getCustomerEntity().getCustomerId())
                    .username(complainEntity.getCustomerEntity().getUsername())
                    .build();
        }

        return ComplainDTO
                .builder()
                .complainId(complainEntity.getComplainId())
                .topic(complainEntity.getTopic())
                .message(complainEntity.getMessage())
                .reply(complainEntity.getReply())
                .status(complainEntity.getStatus())
                .customerMergerDTO(customerMergerDTO)
                .build();
    }

    protected List<ComplainDTO> toDTOs(List<ComplainEntity> complainEntities) {
        return complainEntities.stream()
                .map(complainEntity ->toDTO(complainEntity))
                .collect(Collectors.toList());

    }
}
