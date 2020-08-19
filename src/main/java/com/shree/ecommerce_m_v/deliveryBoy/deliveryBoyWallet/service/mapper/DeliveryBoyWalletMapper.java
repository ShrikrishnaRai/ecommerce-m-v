package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.service.mapper;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.dto.DeliveryBoyWalletDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.entity.DeliveryBoyWalletEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.dto.DeliveryBoyWalletHistoryMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWalletHistory.model.entity.DeliveryBoyWalletHistoryEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DeliveryBoyWalletMapper {

    protected DeliveryBoyWalletDTO toDTO(DeliveryBoyWalletEntity deliveryBoyWalletEntity) {
        DeliveryBoyMergerDTO deliveryBoyMergerDTO = new DeliveryBoyMergerDTO();
        if(deliveryBoyWalletEntity.getDeliveryBoy() != null){
            deliveryBoyMergerDTO = DeliveryBoyMergerDTO.builder()
                    .deliveryBoyId(deliveryBoyWalletEntity.getDeliveryBoy().getDeliveryBoyId())
                    .name(deliveryBoyWalletEntity.getDeliveryBoy().getName())
                    .build();
        }

        List<DeliveryBoyWalletHistoryMergerDTO> deliveryBoyWalletHistoryMergerDTOS = new ArrayList<>();
        if(deliveryBoyWalletEntity.getDeliveryBoyWalletHistories().size()!=0){
            for(int i=0;i<deliveryBoyWalletEntity.getDeliveryBoyWalletHistories().size();i++){
                deliveryBoyWalletHistoryMergerDTOS.add(DeliveryBoyWalletHistoryMergerDTO.builder()
                        .deliveryBoyWalletHistoryId(deliveryBoyWalletEntity.getDeliveryBoyWalletHistories().get(i).getDeliveryBoyWalletHistoryId())
                        .currentAmount(deliveryBoyWalletEntity.getDeliveryBoyWalletHistories().get(i).getCurrentAmount())
                        .build());
            }
        }

        return DeliveryBoyWalletDTO.builder()
                .deliveryBoyWalletId(deliveryBoyWalletEntity.getDeliveryBoyWalletId())
                .currentAmount(deliveryBoyWalletEntity.getCurrentAmount())
                .deliveryBoyMergerDTO(deliveryBoyMergerDTO)
                .deliveryBoyWalletHistoryMergerDTOS(deliveryBoyWalletHistoryMergerDTOS)
                .build();
    }


    protected DeliveryBoyWalletEntity toEntity(DeliveryBoyWalletDTO deliveryBoyWalletDTO) {
        DeliveryBoyInfoEntity deliveryBoyInfoEntity = new DeliveryBoyInfoEntity();
        if(deliveryBoyWalletDTO.getDeliveryBoyMergerDTO() !=null){
            deliveryBoyInfoEntity = DeliveryBoyInfoEntity.builder()
                    .deliveryBoyId(deliveryBoyWalletDTO.getDeliveryBoyMergerDTO().getDeliveryBoyId())
                    .name(deliveryBoyWalletDTO.getDeliveryBoyMergerDTO().getName())
                    .build();
        }

        List<DeliveryBoyWalletHistoryEntity> deliveryBoyWalletHistoryEntities = new ArrayList<>();
        if(deliveryBoyWalletDTO.getDeliveryBoyWalletHistoryMergerDTOS().size()!=0){
            for(int i=0;i<deliveryBoyWalletDTO.getDeliveryBoyWalletHistoryMergerDTOS().size();i++){
                deliveryBoyWalletHistoryEntities.add(DeliveryBoyWalletHistoryEntity.builder()
                        .deliveryBoyWalletHistoryId(deliveryBoyWalletDTO.getDeliveryBoyWalletHistoryMergerDTOS().get(i).getDeliveryBoyWalletHistoryId())
                        .currentAmount(deliveryBoyWalletDTO.getDeliveryBoyWalletHistoryMergerDTOS().get(i).getCurrentAmount())
                        .build());
            }
        }

        return DeliveryBoyWalletEntity.builder()
                .currentAmount(deliveryBoyWalletDTO.getCurrentAmount())
                .deliveryBoy(deliveryBoyWalletDTO.getDeliveryBoyMergerDTO()!=null?deliveryBoyInfoEntity:null)
                .deliveryBoyWalletHistories(deliveryBoyWalletDTO.getDeliveryBoyWalletHistoryMergerDTOS().size()!=0?deliveryBoyWalletHistoryEntities:null)
                .build();

    }

    protected List<DeliveryBoyWalletDTO> toDTOs(List<DeliveryBoyWalletEntity> deliveryBoyWalletEntities) {
        return deliveryBoyWalletEntities.stream()
                .map(deliveryBoyWalletEntity -> toDTO(deliveryBoyWalletEntity))
                .collect(Collectors.toList());

    }
}
