package com.shree.ecommerce_m_v.customer.order.orderService.service;

import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.order.orderService.model.dto.OrderServiceDTO;
import com.shree.ecommerce_m_v.customer.order.orderService.model.entity.OrderServiceEntity;
import com.shree.ecommerce_m_v.customer.order.orderService.repository.OrderServiceRepository;
import com.shree.ecommerce_m_v.customer.order.orderService.service.mapper.OrderServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceServiceClassImpl extends OrderServiceMapper implements OrderServiceServiceClass {

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Override
    public List<OrderServiceDTO> getListOfOrderService() {
        return toDTOs(orderServiceRepository.findAll());
    }

    @Override
    public String saveOrderService(OrderServiceDTO orderServiceDTO) {
        orderServiceRepository.save(toEntity(orderServiceDTO));
        return "Order Service successfully added";
    }

    @Override
    public OrderServiceDTO getOrderServiceById(long id) {
        return toDTO(orderServiceRepository.getOne(id));
    }

    @Override
    public OrderServiceDTO updateOrderService(long id, OrderServiceDTO orderServiceDTO) {
        OrderServiceEntity orderServiceEntity = orderServiceRepository.getOne(id);
        orderServiceEntity.setOrderServiceId(orderServiceDTO.getOrderServiceId());
        orderServiceEntity.setStreet(orderServiceDTO.getStreet());
        orderServiceEntity.setState(orderServiceDTO.getState());
        orderServiceEntity.setDistrict(orderServiceDTO.getDistrict());
        orderServiceEntity.setCity(orderServiceDTO.getCity());
        orderServiceEntity.setServiceTime(orderServiceDTO.getServiceTime());
        orderServiceEntity.setServiceDate(orderServiceDTO.getServiceDate());
        if (orderServiceDTO.getCustomerMergerDTO() != null) {
            orderServiceEntity.setCustomerEntity(CustomerEntity.builder()
                    .customerId(orderServiceDTO.getCustomerMergerDTO().getId())
                    .username(orderServiceDTO.getCustomerMergerDTO().getUsername())
                    .build());
        }
        orderServiceRepository.save(orderServiceEntity);
        return toDTO(orderServiceEntity);
    }

    @Override
    public String deleteOrderService(long id) {
        orderServiceRepository.deleteById(id);
        return "Order Service deleted successfully";
    }
}
