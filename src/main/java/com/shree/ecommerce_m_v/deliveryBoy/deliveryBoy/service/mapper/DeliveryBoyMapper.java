package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.service.mapper;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyInfoDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.dto.DeliveryBoyBankDetailMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.entity.DeliveryBoyBankDetailEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.DTO.DeliveryBoyDocumentMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.entity.DeliveryBoyDocumentEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto.DeliveryBoyPaymentHistoryMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.entity.DeliveryBoyPaymentHistoryEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.dto.DeliveryBoyWalletMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.entity.DeliveryBoyWalletEntity;
import com.shree.ecommerce_m_v.deliveryBoy.task.model.dto.TaskMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.task.model.entity.TaskEntity;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.dto.TeamMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.entity.TeamEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to map entity to dto and vice versa
 */

public abstract class DeliveryBoyMapper {


    protected DeliveryBoyInfoEntity toEntity(DeliveryBoyInfoDTO deliveryBoyInfoDTO) {
        DeliveryBoyInfoEntity deliveryBoyInfoEntity= new DeliveryBoyInfoEntity();
        TeamEntity team = new TeamEntity();
        if (deliveryBoyInfoDTO.getTeamMergerDTO() != null) {
            team = TeamEntity.builder()
                    .teamId(deliveryBoyInfoDTO.getTeamMergerDTO().getTeamId())
                    .teamName(deliveryBoyInfoDTO.getTeamMergerDTO().getTeamName())
                    .build();
        }
        DeliveryBoyDocumentEntity deliveryBoyDocumentEntity= new DeliveryBoyDocumentEntity();
        if (deliveryBoyInfoDTO.getDeliveryBoyDocumentMergerDTO() != null) {
            deliveryBoyDocumentEntity= DeliveryBoyDocumentEntity.builder()
                    .deliveryBoyDocumentId(deliveryBoyInfoDTO.getDeliveryBoyDocumentMergerDTO().getDeliveryBoyDocumentId())
                    .build();

        }

        DeliveryBoyBankDetailEntity boyBankDetailEntity = new DeliveryBoyBankDetailEntity();
        if (deliveryBoyInfoDTO.getDeliveryBoyBankDetailMergerDTO() != null) {
            boyBankDetailEntity = DeliveryBoyBankDetailEntity.builder()
                    .bankId(deliveryBoyInfoDTO.getDeliveryBoyBankDetailMergerDTO().getBankId())
                    .accountHolder(deliveryBoyInfoDTO.getDeliveryBoyBankDetailMergerDTO().getAccountHolder())
                    .build();
        }

        DeliveryBoyWalletEntity boyWalletEntity = new DeliveryBoyWalletEntity();
        if (deliveryBoyInfoDTO.getDeliveryBoyWalletMergerDTO() != null) {
            boyWalletEntity = DeliveryBoyWalletEntity.builder()
                    .deliveryBoyWalletId(deliveryBoyInfoDTO.getDeliveryBoyWalletMergerDTO().getDeliveryBoyWalletId())
                    .currentAmount(deliveryBoyInfoDTO.getDeliveryBoyWalletMergerDTO().getCurrentAmount())
                    .build();
        }

        List<TaskEntity> taskEntities = new ArrayList<>();
        if (deliveryBoyInfoDTO.getTaskMergerDTO().size() != 0) {
            for (int i = 0; i < deliveryBoyInfoDTO.getTaskMergerDTO().size(); i++) {
                taskEntities.add(TaskEntity
                        .builder()
                        .taskId(deliveryBoyInfoDTO.getTaskMergerDTO().get(i).getTaskId())
                        .status(deliveryBoyInfoDTO.getTaskMergerDTO().get(i).getStatus())
                        .build());
            }
        }

        List<DeliveryBoyPaymentHistoryEntity> deliveryBoyPaymentHistoryEntities = new ArrayList<>();
        if (deliveryBoyInfoDTO.getDeliveryBoyPaymentHistoryMergerDTOS().size() != 0) {
            for (int i = 0; i < deliveryBoyInfoDTO.getDeliveryBoyPaymentHistoryMergerDTOS().size(); i++) {
                deliveryBoyPaymentHistoryEntities.add(DeliveryBoyPaymentHistoryEntity
                        .builder()
                        .deliveryBoyPaymentHistoryId(deliveryBoyInfoDTO.getDeliveryBoyPaymentHistoryMergerDTOS().get(i).getDeliveryBoyPaymentHistoryId())
                        .totalAmount(deliveryBoyInfoDTO.getDeliveryBoyPaymentHistoryMergerDTOS().get(i).getTotalAmount())
                        .build());
            }
        }



        return DeliveryBoyInfoEntity
                .builder()
                .name(deliveryBoyInfoDTO.getName())
                .email(deliveryBoyInfoDTO.getEmail())
                .phone(deliveryBoyInfoDTO.getPhone())
                .username(deliveryBoyInfoDTO.getUsername())
                .password(deliveryBoyInfoDTO.getPassword())
                .avatar(deliveryBoyInfoDTO.getAvatar())
                .permanentAddress(deliveryBoyInfoDTO.getPermanentAddress())
                .temporaryAddress(deliveryBoyInfoDTO.getTemporaryAddress())
                .licenseNo(deliveryBoyInfoDTO.getLicenseNo())
                .vehicleType(deliveryBoyInfoDTO.getVehicleType())
                .numberPlateVehicle(deliveryBoyInfoDTO.getNumberPlateVehicle())
                .status(deliveryBoyInfoDTO.getStatus())
                .teamEntity(deliveryBoyInfoDTO.getTeamMergerDTO() != null ? team : null)
                .deliveryBoyDocumentEntity(deliveryBoyInfoDTO.getDeliveryBoyDocumentMergerDTO() !=null ? deliveryBoyDocumentEntity : null)
                .deliveryBoyBankDetailEntity(deliveryBoyInfoDTO.getDeliveryBoyBankDetailMergerDTO() != null ? boyBankDetailEntity :null)
                .deliveryBoyWalletEntity(deliveryBoyInfoDTO.getDeliveryBoyWalletMergerDTO() != null ? boyWalletEntity :null)
                .taskEntity(taskEntities)
                .deliveryBoyPaymentHistoryEntities(deliveryBoyPaymentHistoryEntities)
                .build();


    }

    protected DeliveryBoyInfoDTO toDTO(DeliveryBoyInfoEntity deliveryBoyInfoEntity) {
        TeamMergerDTO teamMergerDTO = new TeamMergerDTO();
        if (deliveryBoyInfoEntity.getTeamEntity() != null) {
            teamMergerDTO = TeamMergerDTO.builder()
                    .teamId(deliveryBoyInfoEntity.getTeamEntity().getTeamId())
                    .teamName(deliveryBoyInfoEntity.getTeamEntity().getTeamName())
                    .build();
        }

        DeliveryBoyDocumentMergerDTO deliveryBoyDocumentMergerDTO = new DeliveryBoyDocumentMergerDTO();
        if (deliveryBoyInfoEntity.getDeliveryBoyDocumentEntity() != null) {
            deliveryBoyDocumentMergerDTO = DeliveryBoyDocumentMergerDTO.builder()
                    .deliveryBoyDocumentId(deliveryBoyInfoEntity.getDeliveryBoyDocumentEntity().getDeliveryBoyDocumentId())
                    .build();

        }

        DeliveryBoyBankDetailMergerDTO deliveryBoyBankDetailMergerDTO = new DeliveryBoyBankDetailMergerDTO();
        if (deliveryBoyInfoEntity.getDeliveryBoyBankDetailEntity() != null) {
            deliveryBoyBankDetailMergerDTO = DeliveryBoyBankDetailMergerDTO.builder()
                    .bankId(deliveryBoyInfoEntity.getDeliveryBoyBankDetailEntity().getBankId())
                    .accountHolder(deliveryBoyInfoEntity.getDeliveryBoyBankDetailEntity().getAccountHolder())
                    .build();
        }

        DeliveryBoyWalletMergerDTO deliveryBoyWalletMergerDTO = new DeliveryBoyWalletMergerDTO();
        if (deliveryBoyInfoEntity.getDeliveryBoyWalletEntity() != null) {
            deliveryBoyWalletMergerDTO = DeliveryBoyWalletMergerDTO.builder()
                    .deliveryBoyWalletId(deliveryBoyInfoEntity.getDeliveryBoyWalletEntity().getDeliveryBoyWalletId())
                    .currentAmount(deliveryBoyInfoEntity.getDeliveryBoyWalletEntity().getCurrentAmount())
                    .build();
        }
        List<TaskMergerDTO> taskMerger = new ArrayList<>();
        if (deliveryBoyInfoEntity.getTaskEntity().size() != 0) {
            for (int i = 0; i < deliveryBoyInfoEntity.getTaskEntity().size(); i++) {
                taskMerger.add(TaskMergerDTO.builder()
                        .taskId(deliveryBoyInfoEntity.getTaskEntity().get(i).getTaskId())
                        .status(deliveryBoyInfoEntity.getTaskEntity().get(i).getStatus())
                        .build());
            }
        }

        List<DeliveryBoyPaymentHistoryMergerDTO> deliveryBoyPaymentHistoryMergerDTOS = new ArrayList<>();
        if (deliveryBoyInfoEntity.getDeliveryBoyPaymentHistoryEntities().size() != 0) {
            for (int i = 0; i < deliveryBoyInfoEntity.getDeliveryBoyPaymentHistoryEntities().size(); i++) {
                deliveryBoyPaymentHistoryMergerDTOS.add(DeliveryBoyPaymentHistoryMergerDTO
                        .builder()
                        .deliveryBoyPaymentHistoryId(deliveryBoyInfoEntity.getDeliveryBoyPaymentHistoryEntities().get(i).getDeliveryBoyPaymentHistoryId())
                        .totalAmount(deliveryBoyInfoEntity.getDeliveryBoyPaymentHistoryEntities().get(i).getTotalAmount())
                        .build());
            }
        }
        return DeliveryBoyInfoDTO.builder()
                .deliveryBoyId(deliveryBoyInfoEntity.getDeliveryBoyId())
                .name(deliveryBoyInfoEntity.getName())
                .email(deliveryBoyInfoEntity.getEmail())
                .phone(deliveryBoyInfoEntity.getPhone())
                .username(deliveryBoyInfoEntity.getUsername())
                .password(deliveryBoyInfoEntity.getPassword())
                .avatar(deliveryBoyInfoEntity.getAvatar())
                .permanentAddress(deliveryBoyInfoEntity.getPermanentAddress())
                .temporaryAddress(deliveryBoyInfoEntity.getTemporaryAddress())
                .licenseNo(deliveryBoyInfoEntity.getLicenseNo())
                .vehicleType(deliveryBoyInfoEntity.getVehicleType())
                .numberPlateVehicle(deliveryBoyInfoEntity.getNumberPlateVehicle())
                .status(deliveryBoyInfoEntity.getStatus())
                .teamMergerDTO(teamMergerDTO)
                .deliveryBoyDocumentMergerDTO(deliveryBoyDocumentMergerDTO)
                .deliveryBoyBankDetailMergerDTO(deliveryBoyBankDetailMergerDTO)
                .deliveryBoyWalletMergerDTO(deliveryBoyWalletMergerDTO)
                .taskMergerDTO(taskMerger)
                .deliveryBoyPaymentHistoryMergerDTOS(deliveryBoyPaymentHistoryMergerDTOS)
                .build();

    }

    protected List<DeliveryBoyInfoDTO> toDTOs(List<DeliveryBoyInfoEntity> deliveryBoyInfoEntities) {

        return deliveryBoyInfoEntities.stream()
                .map(deliveryBoyInfoEntity -> toDTO(deliveryBoyInfoEntity))
                .collect(Collectors.toList());
    }
}
