package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.service.mapper;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.DTO.DeliveryBoyDocumentDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.entity.DeliveryBoyDocumentEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to map Entity to DTO and viceversa
 */

public abstract class DeliveryBoyDocumentMapper {

    protected DeliveryBoyDocumentDTO toDTO(DeliveryBoyDocumentEntity deliveryBoyDocumentEntity) {

        DeliveryBoyMergerDTO deliveryBoyMergerDTO = new DeliveryBoyMergerDTO();
        if(deliveryBoyDocumentEntity.getDeliveryBoy() != null){
            deliveryBoyMergerDTO = DeliveryBoyMergerDTO.builder()
                    .deliveryBoyId(deliveryBoyDocumentEntity.getDeliveryBoy().getDeliveryBoyId())
                    .name(deliveryBoyDocumentEntity.getDeliveryBoy().getName())
                    .build();
        }

        return DeliveryBoyDocumentDTO.builder()
                .deliveryBoyDocumentId(deliveryBoyDocumentEntity.getDeliveryBoyDocumentId())
                .licenseImage(deliveryBoyDocumentEntity.getLicenseImage())
                .bluebookImage(deliveryBoyDocumentEntity.getBluebookImage())
                .citizenshipImage(deliveryBoyDocumentEntity.getCitizenshipImage())
                .vehicleImage(deliveryBoyDocumentEntity.getVehicleImage())
                .deliveryBoyMergerDTO(deliveryBoyMergerDTO)
                .build();

    }

    protected DeliveryBoyDocumentEntity toEntity(DeliveryBoyDocumentDTO deliveryBoyDocumentDTO) {

        DeliveryBoyInfoEntity deliveryBoyInfoEntity = new DeliveryBoyInfoEntity();
        if(deliveryBoyDocumentDTO.getDeliveryBoyMergerDTO() !=null){
            deliveryBoyInfoEntity = DeliveryBoyInfoEntity.builder()
                    .deliveryBoyId(deliveryBoyDocumentDTO.getDeliveryBoyMergerDTO().getDeliveryBoyId())
                    .name(deliveryBoyDocumentDTO.getDeliveryBoyMergerDTO().getName())
                    .build();
        }

        return DeliveryBoyDocumentEntity.builder()
                .licenseImage(deliveryBoyDocumentDTO.getLicenseImage())
                .bluebookImage(deliveryBoyDocumentDTO.getBluebookImage())
                .citizenshipImage(deliveryBoyDocumentDTO.getCitizenshipImage())
                .vehicleImage(deliveryBoyDocumentDTO.getVehicleImage())
                .deliveryBoy(deliveryBoyDocumentDTO.getDeliveryBoyMergerDTO()!=null?deliveryBoyInfoEntity:null)
                .build();
    }

    protected List<DeliveryBoyDocumentDTO> toDTOs(List<DeliveryBoyDocumentEntity> deliveryBoyDocumentEntities) {

        return deliveryBoyDocumentEntities.stream()
                .map(deliveryBoyDocumentEntity -> toDTO(deliveryBoyDocumentEntity))
                .collect(Collectors.toList());
    }
}
