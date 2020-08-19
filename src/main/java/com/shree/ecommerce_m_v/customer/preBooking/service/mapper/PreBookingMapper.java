package com.shree.ecommerce_m_v.customer.preBooking.service.mapper;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerMergerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.preBooking.model.DTO.PreBookingDTO;
import com.shree.ecommerce_m_v.customer.preBooking.model.entity.PreBookingEntity;
import com.shree.ecommerce_m_v.shared.product.product.service.dto.ProductMergerDTO;
import com.shree.ecommerce_m_v.shared.product.product.model.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class PreBookingMapper {

    protected PreBookingEntity toEntity(PreBookingDTO preBookingDTO){
        List<ProductEntity> productEntities = new ArrayList<>();
        if(preBookingDTO.getProductMergerDTOList().size() !=0){
            for (int i=0;i<preBookingDTO.getProductMergerDTOList().size();i++){
                productEntities.add(ProductEntity.builder()
                        .productId(preBookingDTO.getProductMergerDTOList().get(i).getProductId())
                        .productName(preBookingDTO.getProductMergerDTOList().get(i).getProductName())
                        .build());
            }
        }
        CustomerEntity customerEntity = new CustomerEntity();
        if(preBookingDTO.getCustomerMergerDTO() !=null){
            customerEntity=CustomerEntity.builder()
                    .customerId(preBookingDTO.getCustomerMergerDTO().getId())
                    .username(preBookingDTO.getCustomerMergerDTO().getUsername())
                    .build();
        }
        return PreBookingEntity.builder()
                .quantity(preBookingDTO.getQuantity())
                .price(preBookingDTO.getPrice())
                .totalPrice(preBookingDTO.getTotalPrice())
                .status(preBookingDTO.getStatus())
                .customerEntity(preBookingDTO.getCustomerMergerDTO()!=null? customerEntity:null)
                .productEntities(preBookingDTO.getProductMergerDTOList().size()!=0? productEntities: null)
                .build();
    }

    protected PreBookingDTO toDTO(PreBookingEntity preBookingEntity){
        List<ProductMergerDTO> productMergerDTOS = new ArrayList<>();
        if(preBookingEntity.getProductEntities().size() != 0){
            for(int i=0;i<preBookingEntity.getProductEntities().size();i++){
                productMergerDTOS.add(ProductMergerDTO.builder()
                        .productId(preBookingEntity.getProductEntities().get(i).getProductId())
                        .productName(preBookingEntity.getProductEntities().get(i).getProductName())
                        .build());
            }
        }

        CustomerMergerDTO customerMergerDTO = new CustomerMergerDTO();
        if(preBookingEntity.getCustomerEntity() != null){
         customerMergerDTO = CustomerMergerDTO.builder()
                 .id(preBookingEntity.getCustomerEntity().getCustomerId())
                 .username(preBookingEntity.getCustomerEntity().getUsername())
                 .build();
        }

        return PreBookingDTO.builder()
                .preBookingId(preBookingEntity.getPreBookingId())
                .quantity(preBookingEntity.getQuantity())
                .price(preBookingEntity.getPrice())
                .totalPrice(preBookingEntity.getTotalPrice())
                .status(preBookingEntity.getStatus())
                .customerMergerDTO(customerMergerDTO)
                .productMergerDTOList(productMergerDTOS)
                .build();
    }

    protected List<PreBookingDTO> toDTOs(List<PreBookingEntity> preBookingEntities){
        return preBookingEntities.stream()
                .map(preBookingEntity -> toDTO(preBookingEntity))
                .collect(Collectors.toList());
    }
}
