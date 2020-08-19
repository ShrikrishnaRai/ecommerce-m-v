package com.shree.ecommerce_m_v.customer.order.orderService.service.mapper;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.order.orderService.model.dto.OrderServiceDTO;
import com.shree.ecommerce_m_v.customer.order.orderService.model.entity.OrderServiceEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class OrderServiceMapper {

    protected OrderServiceEntity toEntity(OrderServiceDTO orderServiceDTO) {

        CustomerEntity customerEntity = new CustomerEntity();
        if(orderServiceDTO.getCustomerMergerDTO() !=null){
            customerEntity=CustomerEntity.builder()
                    .customerId(orderServiceDTO.getCustomerMergerDTO().getId())
                    .username(orderServiceDTO.getCustomerMergerDTO().getUsername())
                    .build();
        }

        return OrderServiceEntity.builder()
                .orderServiceId(orderServiceDTO.getOrderServiceId())
                .street(orderServiceDTO.getStreet())
                .city(orderServiceDTO.getCity())
                .district(orderServiceDTO.getDistrict())
                .serviceDate(orderServiceDTO.getServiceDate())
                .serviceTime(orderServiceDTO.getServiceTime())
                .customerEntity(orderServiceDTO.getCustomerMergerDTO()!=null? customerEntity: null)
                .build();
    }

    protected OrderServiceDTO toDTO(OrderServiceEntity orderServiceEntity) {
        CustomerMergerDTO customerMergerDTO = new CustomerMergerDTO();
        if (orderServiceEntity.getCustomerEntity() != null) {
            customerMergerDTO = CustomerMergerDTO.builder()
                    .id(orderServiceEntity.getCustomerEntity().getCustomerId())
                    .username(orderServiceEntity.getCustomerEntity().getUsername())
                    .build();
        }
        return OrderServiceDTO.builder()
                .orderServiceId(orderServiceEntity.getOrderServiceId())
                .street(orderServiceEntity.getStreet())
                .city(orderServiceEntity.getCity())
                .district(orderServiceEntity.getDistrict())
                .serviceDate(orderServiceEntity.getServiceDate())
                .serviceTime(orderServiceEntity.getServiceTime())
                .customerMergerDTO(customerMergerDTO)
                .build();
    }

    protected List<OrderServiceDTO> toDTOs(List<OrderServiceEntity> orderServiceEntities) {
        return orderServiceEntities.stream()
                .map(orderServiceEntity -> toDTO(orderServiceEntity))
                .collect(Collectors.toList());
    }
}
