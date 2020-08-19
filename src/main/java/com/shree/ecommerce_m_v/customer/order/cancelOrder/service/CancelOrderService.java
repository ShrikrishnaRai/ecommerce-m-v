package com.shree.ecommerce_m_v.customer.order.cancelOrder.service;

import com.shree.ecommerce_m_v.customer.order.cancelOrder.model.dto.CancelOrderDTO;

import java.util.List;

public interface CancelOrderService {

    List<CancelOrderDTO> getListOfCancelOrder();

    CancelOrderDTO getCancelOrderById(Long cancelOrderId);

    String saveCancelOrder(CancelOrderDTO cancelOrderDTO);

    String deleteCancelOrder(long cancelOrderId);
}
