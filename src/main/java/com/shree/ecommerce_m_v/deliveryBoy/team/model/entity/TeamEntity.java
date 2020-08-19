package com.shree.ecommerce_m_v.deliveryBoy.team.model.entity;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "TeamEntity")
@Table(name = "team")
@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private long teamId;
    private String teamName;
    private String status;

    @OneToMany(mappedBy = "teamEntity", cascade = {CascadeType.MERGE})
    private List<DeliveryBoyInfoEntity> deliveryBoyInfoList = new ArrayList<>();


    public TeamEntity(long teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }


    public void addTeamToDeliveryBoy(DeliveryBoyInfoEntity deliveryBoyInfoEntity) {
        deliveryBoyInfoList.add(deliveryBoyInfoEntity);
        deliveryBoyInfoEntity.setTeamEntity(this);
    }

    public void removeTeamDeliveryBoy(DeliveryBoyInfoEntity deliveryBoyInfoEntity) {
        deliveryBoyInfoList.remove(deliveryBoyInfoEntity);
        deliveryBoyInfoEntity.setTeamEntity(null);
    }
}

