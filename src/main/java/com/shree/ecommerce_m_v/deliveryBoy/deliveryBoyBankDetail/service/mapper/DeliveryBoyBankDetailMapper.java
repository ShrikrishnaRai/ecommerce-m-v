package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.service.mapper;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.dto.DeliveryBoyBankDetailDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.entity.DeliveryBoyBankDetailEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DeliveryBoyBankDetailMapper {


    protected DeliveryBoyBankDetailEntity toEntity(DeliveryBoyBankDetailDTO deliveryBoyBankDetailDTO) {
        DeliveryBoyInfoEntity deliveryBoyInfoEntity = new DeliveryBoyInfoEntity();
        if(deliveryBoyBankDetailDTO.getDeliveryBoyMergerDTO() !=null){
            deliveryBoyInfoEntity = DeliveryBoyInfoEntity.builder()
                    .deliveryBoyId(deliveryBoyBankDetailDTO.getDeliveryBoyMergerDTO().getDeliveryBoyId())
                    .name(deliveryBoyBankDetailDTO.getDeliveryBoyMergerDTO().getName())
                    .build();
        }

        return DeliveryBoyBankDetailEntity.builder()
                .bankId(deliveryBoyBankDetailDTO.getBankId())
                .bankName(deliveryBoyBankDetailDTO.getBankName())
                .accountHolder(deliveryBoyBankDetailDTO.getAccountHolder())
                .accountNumber(deliveryBoyBankDetailDTO.getAccountNumber())
                .deliveryBoy(deliveryBoyBankDetailDTO.getDeliveryBoyMergerDTO()!=null? deliveryBoyInfoEntity:null)
                .build();
    }

    protected DeliveryBoyBankDetailDTO toDTO(DeliveryBoyBankDetailEntity deliveryBoyBankDetailEntity) {

        DeliveryBoyMergerDTO deliveryBoyMergerDTO = new DeliveryBoyMergerDTO();
        if(deliveryBoyBankDetailEntity.getDeliveryBoy() != null){
            deliveryBoyMergerDTO = DeliveryBoyMergerDTO.builder()
                    .deliveryBoyId(deliveryBoyBankDetailEntity.getDeliveryBoy().getDeliveryBoyId())
                    .name(deliveryBoyBankDetailEntity.getDeliveryBoy().getName())
                    .build();
        }
        return DeliveryBoyBankDetailDTO.builder()
                .bankId(deliveryBoyBankDetailEntity.getBankId())
                .bankName(deliveryBoyBankDetailEntity.getBankName())
                .accountHolder(deliveryBoyBankDetailEntity.getAccountHolder())
                .accountNumber(deliveryBoyBankDetailEntity.getAccountNumber())
                .deliveryBoyMergerDTO(deliveryBoyMergerDTO)
                .build();
    }

    protected List<DeliveryBoyBankDetailDTO> toDTOs(List<DeliveryBoyBankDetailEntity> deliveryBoyBankDetailEntities) {

        return deliveryBoyBankDetailEntities.stream()
                .map(deliveryBoyBankDetailEntity -> toDTO(deliveryBoyBankDetailEntity))
                .collect(Collectors.toList());



    }
}
