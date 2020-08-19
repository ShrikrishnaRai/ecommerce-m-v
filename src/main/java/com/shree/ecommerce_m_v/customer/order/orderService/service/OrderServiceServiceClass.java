package com.shree.ecommerce_m_v.customer.order.orderService.service;

import com.shree.ecommerce_m_v.customer.order.orderService.model.dto.OrderServiceDTO;

import java.util.List;

public interface OrderServiceServiceClass {

    List<OrderServiceDTO> getListOfOrderService();

    String saveOrderService(OrderServiceDTO orderServiceDTO);

    OrderServiceDTO getOrderServiceById(long id);

    OrderServiceDTO updateOrderService(long id, OrderServiceDTO orderServiceDTO);

    String deleteOrderService(long id);
}
