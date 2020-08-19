package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto.DeliveryBoyWalletHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto.DeliveryBoyWalletHistoryResponseDTO;
import org.springframework.data.domain.Page;

public interface DeliveryBoyWalletHistoryService {


    Page<DeliveryBoyWalletHistoryResponseDTO> getAllDeliveryBoyWalletHistories(int page);

    DeliveryBoyWalletHistoryResponseDTO getDeliveryBoyWalletHistoryById(long id);

    Double saveDeliveryBoyWalletHistory(DeliveryBoyWalletHistoryDTO deliveryBoyWalletHistoryDTO);

    DeliveryBoyWalletHistoryResponseDTO updateDeliveryBoyWalletHistory(long id, DeliveryBoyWalletHistoryResponseDTO deliveryBoyWalletHistoryResponseDTO);

    String deleteDeliveryBoyWalletHistory(long id);


}
