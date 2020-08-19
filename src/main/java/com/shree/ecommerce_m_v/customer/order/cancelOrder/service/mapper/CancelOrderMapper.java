package com.shree.ecommerce_m_v.customer.order.cancelOrder.service.mapper;

import com.shree.ecommerce_m_v.customer.order.cancelOrder.model.dto.CancelOrderDTO;
import com.shree.ecommerce_m_v.customer.order.cancelOrder.model.entity.CancelOrderEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CancelOrderMapper {

    protected CancelOrderEntity toEntity(CancelOrderDTO cancelOrderDTO) {
        return CancelOrderEntity.builder()
                .cancelOrderId(cancelOrderDTO.getCancelOrderId())
                .reason(cancelOrderDTO.getReason())
                .build();
    }

    protected CancelOrderDTO toDTO(CancelOrderEntity cancelOrderEntity) {
        return CancelOrderDTO.builder()
                .cancelOrderId(cancelOrderEntity.getCancelOrderId())
                .reason(cancelOrderEntity.getReason())
                .build();
    }

    protected List<CancelOrderDTO> toDTOs(List<CancelOrderEntity> cancelOrderEntities) {
        return cancelOrderEntities.stream()
                .map(cancelOrderEntity -> toDTO(cancelOrderEntity))
                .collect(Collectors.toList());
    }
}
