package com.shree.ecommerce_m_v.deliveryBoy.team.service;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.repository.DeliveryBoyRepository;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.dto.TeamDTO;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.dto.TeamIdAndName;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.entity.TeamEntity;
import com.shree.ecommerce_m_v.deliveryBoy.team.repository.TeamRepository;
import com.shree.ecommerce_m_v.deliveryBoy.team.service.mapper.TeamMapper;
import com.shree.ecommerce_m_v.utils.values.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl extends TeamMapper implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    private DeliveryBoyRepository deliveryBoyRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Page<TeamDTO> getListOfTeam(int page, String sortBy, String orderBy) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by(Sort.Direction.valueOf(orderBy),sortBy));
        Page<TeamEntity> result = teamRepository.findAll(pageable);
        return result.map(teamEntity -> toDTO(teamEntity));
    }

    @Override
    public TeamDTO getTeamById(long id) {

        return toDTO(teamRepository.getOne(id));
    }

    @Override
    public List<TeamIdAndName> getTeamIdAndName() {
        return teamRepository.getAllBy();
    }

    @Override
    public String saveTeam(TeamDTO teamDTO) {
        TeamEntity teamEntity = toEntity(teamDTO);
        for (DeliveryBoyMergerDTO deliveryBoyMergerDTO : teamDTO.getDeliveryBoyMergerDTOList()) {
            if (deliveryBoyRepository.existsById(deliveryBoyMergerDTO.getDeliveryBoyId())) {
                teamEntity.addTeamToDeliveryBoy(deliveryBoyRepository.getOne(deliveryBoyMergerDTO.getDeliveryBoyId()));
            }
        }
        teamRepository.saveAndFlush(teamEntity);
        return ResponseValue.SAVE_SUCCESS;
    }

    @Override
    public TeamDTO updateTeam(long id, TeamDTO teamDTO) {
        TeamEntity teamEntity = teamRepository.getOne(id);
        teamEntity.setTeamName(teamDTO.getTeamName());
        teamEntity.setStatus(teamDTO.getStatus());
        if (teamDTO.getDeliveryBoyMergerDTOList() != null) {
            for (DeliveryBoyMergerDTO deliveryBoyMergerDTO : teamDTO.getDeliveryBoyMergerDTOList()) {
                if(deliveryBoyRepository.existsById(deliveryBoyMergerDTO.getDeliveryBoyId())){
                    teamEntity.addTeamToDeliveryBoy(deliveryBoyRepository.getOne(deliveryBoyMergerDTO.getDeliveryBoyId()));
                }
            }
        }
        teamRepository.saveAndFlush(teamEntity);
        return toDTO(teamEntity);
    }

    @Override
    public String deleteTeam(long id) {
        TeamEntity teamEntity= teamRepository.getOne(id);
        if(teamEntity.getDeliveryBoyInfoList()!=null){
            for(int i=teamEntity.getDeliveryBoyInfoList().size()-1;i>=0;--i){
                teamEntity.removeTeamDeliveryBoy(teamEntity.getDeliveryBoyInfoList().get(i));
            }
        }
        teamRepository.deleteById(id);
        return "Team with id " + id + " deleted";

    }

}
