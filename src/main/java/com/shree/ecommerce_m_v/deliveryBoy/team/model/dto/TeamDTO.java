package com.shree.ecommerce_m_v.deliveryBoy.team.model.dto;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyMergerDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeamDTO {

    private long teamId;
    private String teamName;
    private String status;
    private List<DeliveryBoyMergerDTO> deliveryBoyMergerDTOList= new ArrayList<>();


}
