package com.shree.ecommerce_m_v.customer.order.cancelOrder.service;

import com.shree.ecommerce_m_v.customer.order.cancelOrder.model.dto.CancelOrderDTO;
import com.shree.ecommerce_m_v.customer.order.cancelOrder.repository.CancelOrderRepository;
import com.shree.ecommerce_m_v.customer.order.cancelOrder.service.mapper.CancelOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelOrderServiceImpl extends CancelOrderMapper implements CancelOrderService {

    @Autowired
    private CancelOrderRepository cancelOrderRepository;

    @Override
    public List<CancelOrderDTO> getListOfCancelOrder() {
        return toDTOs(cancelOrderRepository.findAll());
    }

    @Override
    public CancelOrderDTO getCancelOrderById(Long cancelOrderId) {
        return toDTO(cancelOrderRepository.getOne(cancelOrderId));
    }

    @Override
    public String saveCancelOrder(CancelOrderDTO cancelOrderDTO) {
        cancelOrderRepository.save(toEntity(cancelOrderDTO));
        return "Added successfully";
    }

    @Override
    public String deleteCancelOrder(long cancelOrderId) {
        cancelOrderRepository.deleteById(cancelOrderId);
        return "Deleted successfully";
    }
}
