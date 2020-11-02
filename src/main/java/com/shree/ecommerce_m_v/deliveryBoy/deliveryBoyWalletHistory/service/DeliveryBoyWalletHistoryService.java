package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.entity.DeliveryBoyWalletEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto.DeliveryBoyWalletHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto.DeliveryBoyWalletHistoryResponseDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.entity.DeliveryBoyWalletHistoryEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.repository.DeliveryBoyWalletHistoryRepository;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.service.mapper.DeliveryBoyWalletHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeliveryBoyWalletHistoryService extends DeliveryBoyWalletHistoryMapper{

    @Autowired
    private DeliveryBoyWalletHistoryRepository deliveryBoyWalletHistoryRepository;

    public Page<DeliveryBoyWalletHistoryResponseDTO> getAllDeliveryBoyWalletHistories(int page) {
        Pageable pageable =PageRequest.of(page,10);
        Page<DeliveryBoyWalletHistoryEntity> result = deliveryBoyWalletHistoryRepository.findAll(pageable);
        return result.map(deliveryBoyWalletHistoryResponseDTO -> toDTO(deliveryBoyWalletHistoryResponseDTO));
    }

    public DeliveryBoyWalletHistoryResponseDTO getDeliveryBoyWalletHistoryById(long id) {
        return toDTO(deliveryBoyWalletHistoryRepository.getOne(id));
    }

    public Double saveDeliveryBoyWalletHistory(DeliveryBoyWalletHistoryDTO deliveryBoyWalletHistoryDTO) {
        return deliveryBoyWalletHistoryRepository.save(toEntity(deliveryBoyWalletHistoryDTO)).getCurrentAmount();
    }

    public DeliveryBoyWalletHistoryResponseDTO updateDeliveryBoyWalletHistory(long id, DeliveryBoyWalletHistoryResponseDTO deliveryBoyWalletHistoryResponseDTO) {

        DeliveryBoyWalletHistoryEntity deliveryBoyWalletHistoryEntity = deliveryBoyWalletHistoryRepository.getOne(id);
        if (deliveryBoyWalletHistoryResponseDTO.getDeliveryBoyWalletMergerDTO() != null) {
           deliveryBoyWalletHistoryEntity.setDeliveryBoyWalletEntity(DeliveryBoyWalletEntity.builder()
                   .deliveryBoyWalletId(deliveryBoyWalletHistoryResponseDTO.getDeliveryBoyWalletMergerDTO().getDeliveryBoyWalletId())
                   .currentAmount(deliveryBoyWalletHistoryResponseDTO.getDeliveryBoyWalletMergerDTO().getCurrentAmount())
                   .build());
        }
        deliveryBoyWalletHistoryEntity.setCurrentAmount(deliveryBoyWalletHistoryResponseDTO.getCurrentAmount());
        deliveryBoyWalletHistoryEntity.setStatus(deliveryBoyWalletHistoryResponseDTO.getStatus());
        deliveryBoyWalletHistoryRepository.saveAndFlush(deliveryBoyWalletHistoryEntity);
        return toDTO(deliveryBoyWalletHistoryEntity);
    }

    public String deleteDeliveryBoyWalletHistory(long id) {
        deliveryBoyWalletHistoryRepository.deleteById(id);
        return "Delivery Boy Wallet History with id :" + id + " deleted";
    }
}
