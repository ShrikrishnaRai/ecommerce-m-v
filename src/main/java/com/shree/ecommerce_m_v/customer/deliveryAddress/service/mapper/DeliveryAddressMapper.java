package com.shree.ecommerce_m_v.customer.deliveryAddress.service.mapper;

import com.shree.ecommerce_m_v.customer.deliveryAddress.model.DTO.DeliveryAddressDTO;
import com.shree.ecommerce_m_v.customer.deliveryAddress.model.entity.DeliveryAddressEntity;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderMergerDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.entity.OrderEntity;

public  abstract class DeliveryAddressMapper {

    protected DeliveryAddressEntity toEntity(DeliveryAddressDTO deliveryAddressDTO){
        OrderEntity orderEntity= new OrderEntity();
        if(deliveryAddressDTO.getOrderMergerDTO()!=null){
            orderEntity= OrderEntity.builder()
                    .orderId(deliveryAddressDTO.getOrderMergerDTO().getId())
                    .totalAmount(deliveryAddressDTO.getOrderMergerDTO().getTotalAmount())
                    .build();
        }

        return DeliveryAddressEntity.builder()
                .street(deliveryAddressDTO.getStreet())
                .city(deliveryAddressDTO.getCity())
                .district(deliveryAddressDTO.getDistrict())
                .state(deliveryAddressDTO.getState())
                .contactNo(deliveryAddressDTO.getContactNo())
                .orderEntity(deliveryAddressDTO.getOrderMergerDTO() !=null? orderEntity: null)
                .build();
    }

    protected DeliveryAddressDTO toDTO(DeliveryAddressEntity deliveryAddressEntity){
        OrderMergerDTO orderMergerDTO= new OrderMergerDTO();
        if(deliveryAddressEntity.getOrderEntity()!=null){
            orderMergerDTO= OrderMergerDTO.builder()
                    .id(deliveryAddressEntity.getOrderEntity().getOrderId())
                    .totalAmount(deliveryAddressEntity.getOrderEntity().getTotalAmount())
                    .build();
        }

        return DeliveryAddressDTO.builder()
                .deliveryAddressId(deliveryAddressEntity.getDeliveryAddressId())
                .street(deliveryAddressEntity.getStreet())
                .city(deliveryAddressEntity.getCity())
                .district(deliveryAddressEntity.getDistrict())
                .state(deliveryAddressEntity.getState())
                .contactNo(deliveryAddressEntity.getContactNo())
                .orderMergerDTO(orderMergerDTO)
                .build();
    }
}
