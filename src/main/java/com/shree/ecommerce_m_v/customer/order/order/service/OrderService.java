package com.shree.ecommerce_m_v.customer.order.order.service;

import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderResponseDTO.OrderRequestDTO;
import com.shree.ecommerce_m_v.customer.order.order.model.DTO.OrderResponseDTO.OrderResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Page<OrderResponseDTO> getOrders(int page, String sortBy, String orderBy);

    String saveOrder(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO getOrderById(final Long orderId);

    OrderResponseDTO updateOrder(final Long orderId, OrderRequestDTO orderRequestDTO);

    List<OrderResponseDTO> getOrderByCustomerId(final Long customerId);

}
