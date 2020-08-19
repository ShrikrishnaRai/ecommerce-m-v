package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.service.mapper;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto.DeliveryBoyPaymentHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto.DeliveryBoyPaymentHistoryResponseDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.entity.DeliveryBoyPaymentHistoryEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DeliveryBoyPaymentHistoryMapper {

    protected DeliveryBoyPaymentHistoryEntity toEntity(DeliveryBoyPaymentHistoryDTO deliveryBoyPaymentHistoryDTO) {
        DeliveryBoyInfoEntity deliveryBoyInfoEntity = new DeliveryBoyInfoEntity();
        if(deliveryBoyPaymentHistoryDTO.getDeliveryBoyMergerDTO() !=null){
            deliveryBoyInfoEntity = DeliveryBoyInfoEntity.builder()
                    .deliveryBoyId(deliveryBoyPaymentHistoryDTO.getDeliveryBoyMergerDTO().getDeliveryBoyId())
                    .name(deliveryBoyPaymentHistoryDTO.getDeliveryBoyMergerDTO().getName())
                    .build();
        }

        return DeliveryBoyPaymentHistoryEntity.builder()
                .totalAmount(deliveryBoyPaymentHistoryDTO.getTotalAmount())
                .status(deliveryBoyPaymentHistoryDTO.getStatus())
                .deliveryBoyInfoEntity(deliveryBoyPaymentHistoryDTO.getDeliveryBoyMergerDTO()!=null? deliveryBoyInfoEntity:null)
                .build();
    }

    protected DeliveryBoyPaymentHistoryResponseDTO toDTO(DeliveryBoyPaymentHistoryEntity deliveryBoyPaymentHistoryEntity) {

        DeliveryBoyMergerDTO deliveryBoyMergerDTO = new DeliveryBoyMergerDTO();
        if(deliveryBoyPaymentHistoryEntity.getDeliveryBoyInfoEntity() != null){
            deliveryBoyMergerDTO = DeliveryBoyMergerDTO.builder()
                    .deliveryBoyId(deliveryBoyPaymentHistoryEntity.getDeliveryBoyInfoEntity().getDeliveryBoyId())
                    .name(deliveryBoyPaymentHistoryEntity.getDeliveryBoyInfoEntity().getName())
                    .build();
        }

        return DeliveryBoyPaymentHistoryResponseDTO.builder()
                .deliveryBoyPaymentHistoryId(deliveryBoyPaymentHistoryEntity.getDeliveryBoyPaymentHistoryId())
                .totalAmount(deliveryBoyPaymentHistoryEntity.getTotalAmount())
                .status(deliveryBoyPaymentHistoryEntity.getStatus())
                .dateCreated(deliveryBoyPaymentHistoryEntity.getDateCreated())
                .dateModified(deliveryBoyPaymentHistoryEntity.getDateModified())
                .deliveryBoyMergerDTO(deliveryBoyMergerDTO)
                .build();

    }

    protected List<DeliveryBoyPaymentHistoryResponseDTO> toDTOs(List<DeliveryBoyPaymentHistoryEntity> deliveryBoyPaymentHistoryEntities) {

        return deliveryBoyPaymentHistoryEntities.stream()
                .map(deliveryBoyPaymentHistoryEntity -> toDTO(deliveryBoyPaymentHistoryEntity))
                .collect(Collectors.toList());
    }

}
