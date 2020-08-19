package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyIdAndName;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyInfoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DeliveryBoyService {

    Page<DeliveryBoyInfoDTO> getAllDeliveryBoys(int page);

    String saveDeliveryBoy(DeliveryBoyInfoDTO deliveryBoyInfoDTO);

    DeliveryBoyInfoDTO getDeliveryBoyById(long id);

    String deleteDeliveryBoy(long id);

    Page<DeliveryBoyInfoDTO> getDeliveryBoyByName(String name);

    DeliveryBoyInfoDTO updateDeliveryBoy(long id, DeliveryBoyInfoDTO deliveryBoyInfoDTO);

    List<DeliveryBoyIdAndName> getDeliveryBoyIdAndName();
}
