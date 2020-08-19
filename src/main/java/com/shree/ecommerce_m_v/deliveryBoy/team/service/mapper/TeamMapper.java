package com.shree.ecommerce_m_v.deliveryBoy.team.service.mapper;


import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.dto.TeamDTO;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.entity.TeamEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class to map team entity to dto and vice-versa
 */
public abstract class TeamMapper {

    private List<DeliveryBoyInfoEntity> deliveryBoyInfoEntityList = new ArrayList<>();

    public List<DeliveryBoyInfoEntity> getDeliveryBoyInfoEntityList() {
        return this.deliveryBoyInfoEntityList;
    }

    protected TeamEntity toEntity(TeamDTO teamDTO) {
        if (teamDTO.getDeliveryBoyMergerDTOList().size() != 0) {
            for (int i = 0; i < teamDTO.getDeliveryBoyMergerDTOList().size(); i++) {
                deliveryBoyInfoEntityList.add(DeliveryBoyInfoEntity.builder()
                        .deliveryBoyId(teamDTO.getDeliveryBoyMergerDTOList().get(i).getDeliveryBoyId())
                        .name(teamDTO.getDeliveryBoyMergerDTOList().get(i).getName())
                        .build());
            }
        }
        return TeamEntity.builder()
                .teamName(teamDTO.getTeamName())
                .status(teamDTO.getStatus())
                .deliveryBoyInfoList(deliveryBoyInfoEntityList)
                .build();
    }

    protected TeamDTO toDTO(TeamEntity teamEntity) {
        List<DeliveryBoyMergerDTO> deliveryBoyMergerDTOS = new ArrayList<>();
        if (teamEntity.getDeliveryBoyInfoList().size() != 0) {
            for (int i = 0; i < teamEntity.getDeliveryBoyInfoList().size(); i++) {
                deliveryBoyMergerDTOS.add(DeliveryBoyMergerDTO.builder()
                        .deliveryBoyId(teamEntity.getDeliveryBoyInfoList().get(i).getDeliveryBoyId())
                        .name(teamEntity.getDeliveryBoyInfoList().get(i).getName())
                        .build());
            }
        }
        return TeamDTO.builder()
                .teamId(teamEntity.getTeamId())
                .teamName(teamEntity.getTeamName())
                .status(teamEntity.getStatus())
                .deliveryBoyMergerDTOList(deliveryBoyMergerDTOS)
                .build();
    }

    protected List<TeamDTO> toDTOs(List<TeamEntity> teamEntities) {
        return teamEntities.stream()
                .map(teamEntity -> toDTO(teamEntity))
                .collect(Collectors.toList());
    }
}
