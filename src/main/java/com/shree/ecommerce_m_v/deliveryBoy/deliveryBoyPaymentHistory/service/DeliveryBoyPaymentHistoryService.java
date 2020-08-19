package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto.DeliveryBoyPaymentHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto.DeliveryBoyPaymentHistoryResponseDTO;
import org.springframework.data.domain.Page;

public interface DeliveryBoyPaymentHistoryService {

    Page<DeliveryBoyPaymentHistoryResponseDTO> getListOfDeliveryBoyPaymentHistory(int page);

    DeliveryBoyPaymentHistoryResponseDTO getDeliveryBoyPaymentHistoryById(long id);

    String saveDeliveryBoyPaymentHistory(DeliveryBoyPaymentHistoryDTO deliveryBoyPaymentHistoryDTO);

    DeliveryBoyPaymentHistoryResponseDTO updateDeliveryBoyPaymentHistory(long id, DeliveryBoyPaymentHistoryDTO deliveryBoyPaymentHistoryDTO);

    String deleteDeliveryBoyPaymentHistory(long id);

}
