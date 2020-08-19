package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto.DeliveryBoyPaymentHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto.DeliveryBoyPaymentHistoryResponseDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.entity.DeliveryBoyPaymentHistoryEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.repository.DeliveryBoyPaymentHistoryRepository;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.service.mapper.DeliveryBoyPaymentHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeliveryBoyPaymentHistoryServiceImpl extends DeliveryBoyPaymentHistoryMapper implements DeliveryBoyPaymentHistoryService {

    @Autowired
    private DeliveryBoyPaymentHistoryRepository deliveryBoyPaymentHistoryRepository;

    @Override
    public Page<DeliveryBoyPaymentHistoryResponseDTO> getListOfDeliveryBoyPaymentHistory(int page) {
        Pageable pageable= PageRequest.of(page,10);
        Page<DeliveryBoyPaymentHistoryEntity> result = deliveryBoyPaymentHistoryRepository.findAll(pageable);
        return result.map(deliveryBoyPaymentHistoryEntity -> toDTO(deliveryBoyPaymentHistoryEntity));
    }

    @Override
    public DeliveryBoyPaymentHistoryResponseDTO getDeliveryBoyPaymentHistoryById(long id) {
        return toDTO(deliveryBoyPaymentHistoryRepository.getOne(id));
    }

    @Override
    public String saveDeliveryBoyPaymentHistory(DeliveryBoyPaymentHistoryDTO deliveryBoyPaymentHistoryDTO) {
        deliveryBoyPaymentHistoryRepository.save(toEntity(deliveryBoyPaymentHistoryDTO));
        return "Added successfully";
    }

    @Override
    public DeliveryBoyPaymentHistoryResponseDTO updateDeliveryBoyPaymentHistory(long id, DeliveryBoyPaymentHistoryDTO deliveryBoyPaymentHistoryDTO) {
        DeliveryBoyPaymentHistoryEntity deliveryBoyPaymentHistoryEntity = deliveryBoyPaymentHistoryRepository.getOne(id);
        deliveryBoyPaymentHistoryEntity.setTotalAmount(deliveryBoyPaymentHistoryDTO.getTotalAmount());
        deliveryBoyPaymentHistoryEntity.setStatus(deliveryBoyPaymentHistoryDTO.getStatus());
        if (deliveryBoyPaymentHistoryDTO.getDeliveryBoyMergerDTO() != null) {
            deliveryBoyPaymentHistoryEntity.setDeliveryBoyInfoEntity(DeliveryBoyInfoEntity.builder()
                    .deliveryBoyId(deliveryBoyPaymentHistoryDTO.getDeliveryBoyMergerDTO().getDeliveryBoyId())
                    .name(deliveryBoyPaymentHistoryDTO.getDeliveryBoyMergerDTO().getName())
                    .build());
        }
        deliveryBoyPaymentHistoryRepository.save(deliveryBoyPaymentHistoryEntity);
        return toDTO(deliveryBoyPaymentHistoryEntity);
    }

    @Override
    public String deleteDeliveryBoyPaymentHistory(long id) {
        deliveryBoyPaymentHistoryRepository.deleteById(id);
        return "Deleted successfully";
    }
}
