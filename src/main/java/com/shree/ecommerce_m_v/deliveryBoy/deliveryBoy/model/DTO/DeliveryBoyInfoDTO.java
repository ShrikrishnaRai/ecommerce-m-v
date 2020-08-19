package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO;

import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.Status;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.VehicleType;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.dto.DeliveryBoyBankDetailMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.DTO.DeliveryBoyDocumentMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyPaymentHistory.model.dto.DeliveryBoyPaymentHistoryMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.dto.DeliveryBoyWalletMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.task.model.dto.TaskMergerDTO;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.dto.TeamMergerDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryBoyInfoDTO {

    private long deliveryBoyId;
    private String name;
    private String email;
    private String phone;
    private String permanentAddress;
    private String temporaryAddress;
    private String citizenshipNo;
    private String licenseNo;
    private VehicleType vehicleType;
    private String numberPlateVehicle;
    private Status status;

    private String username;
    private String password;
    private String avatar;

    private TeamMergerDTO teamMergerDTO;

    private DeliveryBoyDocumentMergerDTO deliveryBoyDocumentMergerDTO;

    private DeliveryBoyBankDetailMergerDTO deliveryBoyBankDetailMergerDTO;

    private DeliveryBoyWalletMergerDTO deliveryBoyWalletMergerDTO;

    private List<TaskMergerDTO> taskMergerDTO = new ArrayList<>();

    private List<DeliveryBoyPaymentHistoryMergerDTO> deliveryBoyPaymentHistoryMergerDTOS = new ArrayList<>();



}
