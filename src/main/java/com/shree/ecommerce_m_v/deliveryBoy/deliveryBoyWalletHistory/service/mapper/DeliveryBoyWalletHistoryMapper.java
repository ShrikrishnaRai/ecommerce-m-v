package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.service.mapper;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.dto.DeliveryBoyWalletMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.entity.DeliveryBoyWalletEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto.DeliveryBoyWalletHistoryDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto.DeliveryBoyWalletHistoryResponseDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.entity.DeliveryBoyWalletHistoryEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class to map DeliveryBoyWalletHistoryTransaction and viceversa
 */

public abstract class DeliveryBoyWalletHistoryMapper {

    protected DeliveryBoyWalletHistoryEntity toEntity(DeliveryBoyWalletHistoryDTO deliveryBoyWalletHistoryDTO) {

        DeliveryBoyWalletEntity deliveryBoyWalletEntity = new DeliveryBoyWalletEntity();
        if(deliveryBoyWalletHistoryDTO.getDeliveryBoyWalletMergerDTO() != null){
            deliveryBoyWalletEntity= DeliveryBoyWalletEntity.builder()
                    .deliveryBoyWalletId(deliveryBoyWalletHistoryDTO.getDeliveryBoyWalletMergerDTO().getDeliveryBoyWalletId())
                    .currentAmount(deliveryBoyWalletHistoryDTO.getDeliveryBoyWalletMergerDTO().getCurrentAmount())
                    .build();
        }
        return DeliveryBoyWalletHistoryEntity.builder()
                .currentAmount(deliveryBoyWalletHistoryDTO.getCurrentAmount())
                .status(deliveryBoyWalletHistoryDTO.getStatus())
                .deliveryBoyWalletEntity(deliveryBoyWalletEntity)
                .build();

    }

    protected DeliveryBoyWalletHistoryResponseDTO toDTO(DeliveryBoyWalletHistoryEntity deliveryBoyWalletHistoryEntity) {
        DeliveryBoyWalletMergerDTO deliveryBoyWalletMergerDTO = new DeliveryBoyWalletMergerDTO();
        if(deliveryBoyWalletHistoryEntity.getDeliveryBoyWalletEntity() != null){
            deliveryBoyWalletMergerDTO= DeliveryBoyWalletMergerDTO.builder()
                    .deliveryBoyWalletId(deliveryBoyWalletHistoryEntity.getDeliveryBoyWalletEntity().getDeliveryBoyWalletId())
                    .currentAmount(deliveryBoyWalletHistoryEntity.getDeliveryBoyWalletEntity().getCurrentAmount())
                    .build();
        }

        return DeliveryBoyWalletHistoryResponseDTO.builder()
                .deliveryBoyWalletHistoryId(deliveryBoyWalletHistoryEntity.getDeliveryBoyWalletHistoryId())
                .currentAmount(deliveryBoyWalletHistoryEntity.getCurrentAmount())
                .status(deliveryBoyWalletHistoryEntity.getStatus())
                .dateCreated(deliveryBoyWalletHistoryEntity.getDateCreated())
                .dateModified(deliveryBoyWalletHistoryEntity.getDateModified())
                .timeStamp(deliveryBoyWalletHistoryEntity.getTimeStamp())
                .deliveryBoyWalletMergerDTO(deliveryBoyWalletMergerDTO)
                .build();
    }

    protected List<DeliveryBoyWalletHistoryResponseDTO> toDTOs(List<DeliveryBoyWalletHistoryEntity> deliveryBoyWalletHistories) {
        return deliveryBoyWalletHistories.stream()
                .map(deliveryBoyWalletHistoryEntity -> toDTO(deliveryBoyWalletHistoryEntity))
                .collect(Collectors.toList());
    }


}
