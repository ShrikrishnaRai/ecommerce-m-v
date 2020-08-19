package com.shree.ecommerce_m_v.deliveryBoy.team.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamMergerDTO {

    private long teamId;
    private String teamName;


}
