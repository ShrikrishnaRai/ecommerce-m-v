package com.shree.ecommerce_m_v.deliveryBoy.team.repository;

import com.shree.ecommerce_m_v.deliveryBoy.team.model.dto.TeamIdAndName;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    List<TeamIdAndName> getAllBy();


}
