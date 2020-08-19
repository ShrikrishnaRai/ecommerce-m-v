package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity;


import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.entity.DeliveryBoyBankDetailEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.entity.DeliveryBoyDocumentEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.entity.DeliveryBoyPaymentHistoryEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.entity.DeliveryBoyWalletEntity;
import com.shree.ecommerce_m_v.deliveryBoy.task.model.entity.TaskEntity;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.entity.TeamEntity;
import com.shree.ecommerce_m_v.utils.domain.AbstractAuditingEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "delivery_boy")
@Entity(name="DeliveryBoyInfoEntity")
public class DeliveryBoyInfoEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_boy_id")
    private long deliveryBoyId;
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    private String phone;
    @Column(unique = true)
    private String username;
    private String password;
    private String avatar;
    private String permanentAddress;
    private String temporaryAddress;
    @Column(unique = true)
    private String citizenshipNo;
    @Column(unique = true)
    private String licenseNo;
    private VehicleType vehicleType;
    private String numberPlateVehicle;
    private Status status;

    @ManyToOne(cascade ={CascadeType.MERGE} )
    @JoinColumn(name = "team_id_fk", referencedColumnName = "team_id")
    private TeamEntity teamEntity;

    @OneToOne(mappedBy = "deliveryBoy",cascade = {CascadeType.MERGE})
    private DeliveryBoyDocumentEntity deliveryBoyDocumentEntity;

    @OneToOne(mappedBy = "deliveryBoy",cascade = {CascadeType.MERGE})
    private DeliveryBoyBankDetailEntity deliveryBoyBankDetailEntity;

    @OneToOne(mappedBy = "deliveryBoy",cascade = {CascadeType.MERGE})
    private DeliveryBoyWalletEntity deliveryBoyWalletEntity;

    @OneToMany(mappedBy = "deliveryBoy", cascade = {CascadeType.MERGE})
    private List<TaskEntity> taskEntity= new ArrayList<>();

    @OneToMany(mappedBy = "deliveryBoyInfoEntity",cascade = {CascadeType.MERGE})
    private List<DeliveryBoyPaymentHistoryEntity> deliveryBoyPaymentHistoryEntities= new ArrayList<>();

    public DeliveryBoyInfoEntity(Long deliveryBoyId,String name) {
        this.deliveryBoyId=deliveryBoyId;
        this.name=name;
    }
}