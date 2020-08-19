package com.shree.ecommerce_m_v.customer.customer.service.mapper;

import com.shree.ecommerce_m_v.customer.complain.service.dto.ComplainMergerDTO;
import com.shree.ecommerce_m_v.customer.complain.model.entity.ComplainEntity;
import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.customerWallet.model.dto.CustomerWalletMergerDTO;
import com.shree.ecommerce_m_v.customer.customerWallet.model.entity.CustomerWalletEntity;
import com.shree.ecommerce_m_v.customer.order.orderService.model.dto.OrderServiceMergerDTO;
import com.shree.ecommerce_m_v.customer.order.orderService.model.entity.OrderServiceEntity;
import com.shree.ecommerce_m_v.customer.preBooking.model.DTO.PreBookingMergerDTO;
import com.shree.ecommerce_m_v.customer.preBooking.model.entity.PreBookingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CustomerMapper {

    private List<ComplainEntity> complainEntities=new ArrayList<>();
    private List<PreBookingEntity> preBookingEntities= new ArrayList<>();
    private List<OrderServiceEntity> orderServiceEntities= new ArrayList<>();

    public List<ComplainEntity> getComplainEntities(){return this.complainEntities;}
    public List<PreBookingEntity> getPreBookingEntities(){ return this.preBookingEntities; }
    public List<OrderServiceEntity> getOrderServiceEntities(){return this.orderServiceEntities;}


    protected CustomerEntity toEntity(CustomerDTO customerDTO) {

        if(customerDTO.getComplainMergerDTOS().size()!=0){
            for(ComplainMergerDTO complainMergerDTO:customerDTO.getComplainMergerDTOS()){
                complainEntities.add(ComplainEntity.builder()
                        .complainId(complainMergerDTO.getComplainId())
                        .topic(complainMergerDTO.getTopic())
                        .build());
            }
        }

        if(customerDTO.getPreBookingMergerDTOList().size()!=0){
            for(PreBookingMergerDTO preBookingMergerDTO:customerDTO.getPreBookingMergerDTOList()){
                preBookingEntities.add(PreBookingEntity.builder()
                        .preBookingId(preBookingMergerDTO.getPreBookingId())
                        .quantity(preBookingMergerDTO.getQuantity())
                        .build());
            }
        }

        if(customerDTO.getOrderServiceMergerDTOS().size()!=0){
            for(OrderServiceMergerDTO orderServiceMergerDTO:customerDTO.getOrderServiceMergerDTOS()){
                orderServiceEntities.add(OrderServiceEntity.builder()
                        .orderServiceId(orderServiceMergerDTO.getOrderServiceId())
                        .street(orderServiceMergerDTO.getStreet())
                        .build());
            }
        }

        CustomerWalletEntity customerWalletEntity= new CustomerWalletEntity();
        if(customerDTO.getCustomerWalletMergerDTO()!=null){
            customerWalletEntity=CustomerWalletEntity.builder()
                    .customerWalletId(customerDTO.getCustomerWalletMergerDTO().getCustomerWalletId())
                    .currentAmount(customerDTO.getCustomerWalletMergerDTO().getCurrentAmount())
                    .build();
        }

        return CustomerEntity
                .builder()
                .customerName(customerDTO.getCustomerName())
                .contactNo(customerDTO.getContactNo())
                .address(customerDTO.getAddress())
                .zipCode(customerDTO.getZipCode())
                .email(customerDTO.getEmail())
                .username(customerDTO.getUsername())
                .password(customerDTO.getPassword())
                .verificationCode(customerDTO.getVerificationCode())
                .status(customerDTO.getStatus())
                .image(customerDTO.getImage())
                .customerWalletEntity(customerDTO.getCustomerWalletMergerDTO()!=null? customerWalletEntity: null)
                .complainEntities(customerDTO.getComplainMergerDTOS().size()!=0? complainEntities:null)
                .preBookingEntityList(customerDTO.getPreBookingMergerDTOList().size()!=0? preBookingEntities:null)
                .orderServiceEntities(customerDTO.getOrderServiceMergerDTOS().size()!=0? orderServiceEntities:null)
                .build();
    }

    protected CustomerDTO toDTO(CustomerEntity customerEntity) {

        List<ComplainMergerDTO> complainMergerDTOS= new ArrayList<>();
        if(customerEntity.getComplainEntities().size()!=0){
            for(ComplainEntity complainEntity:customerEntity.getComplainEntities()){
                complainMergerDTOS.add(ComplainMergerDTO.builder()
                        .complainId(complainEntity.getComplainId())
                        .topic(complainEntity.getTopic())
                        .build());
            }
        }

        List<PreBookingMergerDTO> preBookingMergerDTOS= new ArrayList<>();
        if(customerEntity.getPreBookingEntityList().size()!=0){
            for(PreBookingEntity preBookingEntity:customerEntity.getPreBookingEntityList()){
                preBookingMergerDTOS.add(PreBookingMergerDTO.builder()
                        .preBookingId(preBookingEntity.getPreBookingId())
                        .quantity(preBookingEntity.getQuantity())
                        .build());
            }
        }

        List<OrderServiceMergerDTO> orderServiceMergerDTOS= new ArrayList<>();
        if(customerEntity.getOrderServiceEntities().size()!=0){
            for(OrderServiceEntity orderServiceEntity:customerEntity.getOrderServiceEntities()){
                orderServiceMergerDTOS.add(OrderServiceMergerDTO.builder()
                        .orderServiceId(orderServiceEntity.getOrderServiceId())
                        .street(orderServiceEntity.getStreet())
                        .build());
            }
        }

        CustomerWalletMergerDTO customerWalletMergerDTO= new CustomerWalletMergerDTO();
        if(customerEntity.getCustomerWalletEntity()!=null){
            customerWalletMergerDTO=CustomerWalletMergerDTO.builder()
                    .customerWalletId(customerEntity.getCustomerWalletEntity().getCustomerWalletId())
                    .currentAmount(customerEntity.getCustomerWalletEntity().getCurrentAmount())
                    .build();
        }
      return CustomerDTO.builder()
              .customerId(customerEntity.getCustomerId())
              .customerName(customerEntity.getCustomerName())
              .contactNo(customerEntity.getContactNo())
              .address(customerEntity.getAddress())
              .zipCode(customerEntity.getZipCode())
              .email(customerEntity.getEmail())
              .username(customerEntity.getUsername())
              .password(customerEntity.getPassword())
              .verificationCode(customerEntity.getVerificationCode())
              .status(customerEntity.getStatus())
              .image(customerEntity.getImage())
              .complainMergerDTOS(complainMergerDTOS)
              .preBookingMergerDTOList(preBookingMergerDTOS)
              .customerWalletMergerDTO(customerWalletMergerDTO)
              .orderServiceMergerDTOS(orderServiceMergerDTOS)
              .build();
    }


    protected List<CustomerDTO> toDTOList(List<CustomerEntity> customerEntityList) {
        return customerEntityList.stream()
                .map(customerEntity -> toDTO(customerEntity))
                .collect(Collectors.toList());
    }
}
