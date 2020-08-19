package com.shree.ecommerce_m_v.deliveryBoy.team.service;

import com.shree.ecommerce_m_v.deliveryBoy.team.model.dto.TeamDTO;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.dto.TeamIdAndName;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeamService {

    Page<TeamDTO> getListOfTeam(int page, String sortBy, String orderBy);

    TeamDTO getTeamById(long id);

    List<TeamIdAndName> getTeamIdAndName();

    String saveTeam(TeamDTO teamDTO);

    TeamDTO updateTeam(long id, TeamDTO teamDTO);

    String deleteTeam(long id);


}
