package com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.service;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyIdAndName;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.DTO.DeliveryBoyInfoDTO;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.model.entity.DeliveryBoyInfoEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.repository.DeliveryBoyRepository;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoy.service.mapper.DeliveryBoyMapper;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyBankDetail.model.entity.DeliveryBoyBankDetailEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyDocument.model.entity.DeliveryBoyDocumentEntity;
import com.shree.ecommerce_m_v.deliveryBoy.deliveryBoyWallet.model.entity.DeliveryBoyWalletEntity;
import com.shree.ecommerce_m_v.deliveryBoy.team.model.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeliveryBoyServiceImpl extends DeliveryBoyMapper implements DeliveryBoyService {

    @Autowired
    private DeliveryBoyRepository deliveryBoyRepository;

    @Override
    public Page<DeliveryBoyInfoDTO> getAllDeliveryBoys(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<DeliveryBoyInfoEntity> result = deliveryBoyRepository.findAll(pageable);
        return result.map(this::toDTO);
    }

    @Override
    public String saveDeliveryBoy(DeliveryBoyInfoDTO deliveryBoyInfoDTO) {
        deliveryBoyRepository.save(toEntity(deliveryBoyInfoDTO));
        return "Added successfully";
    }

    @Override
    public DeliveryBoyInfoDTO getDeliveryBoyById(long id) {

        return toDTO(deliveryBoyRepository.getOne(id));
    }

    @Override
    public String deleteDeliveryBoy(long id) {

        deliveryBoyRepository.deleteById(id);
        return "Delivery Boy with id : " + id + " deleted";
    }

    @Override
    public Page<DeliveryBoyInfoDTO> getDeliveryBoyByName(String name) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<DeliveryBoyInfoEntity> result = deliveryBoyRepository.getDeliveryBoyByName(name,pageable);
        return result.map(deliveryBoyInfoEntity -> toDTO(deliveryBoyInfoEntity));
    }

    @Override
    public DeliveryBoyInfoDTO updateDeliveryBoy(long id, DeliveryBoyInfoDTO deliveryBoyInfoDTO) {

        DeliveryBoyInfoEntity deliveryBoyInfoEntity = deliveryBoyRepository.getOne(id);

        if (deliveryBoyInfoDTO.getTeamMergerDTO() != null) {
            deliveryBoyInfoEntity.setTeamEntity(TeamEntity.builder()
                    .teamId(deliveryBoyInfoDTO.getTeamMergerDTO().getTeamId())
                    .teamName(deliveryBoyInfoDTO.getTeamMergerDTO().getTeamName())
                    .build());
        }

        if (deliveryBoyInfoDTO.getDeliveryBoyDocumentMergerDTO() != null) {
            deliveryBoyInfoEntity.setDeliveryBoyDocumentEntity(
                    DeliveryBoyDocumentEntity.builder()
                            .deliveryBoyDocumentId(deliveryBoyInfoDTO.getDeliveryBoyDocumentMergerDTO().getDeliveryBoyDocumentId())
                            .build());
        }

        if (deliveryBoyInfoDTO.getDeliveryBoyBankDetailMergerDTO() != null) {
            deliveryBoyInfoEntity.setDeliveryBoyBankDetailEntity(DeliveryBoyBankDetailEntity.builder()
                    .bankId(deliveryBoyInfoDTO.getDeliveryBoyBankDetailMergerDTO().getBankId())
                    .accountHolder(deliveryBoyInfoDTO.getDeliveryBoyBankDetailMergerDTO().getAccountHolder())
                    .build());
        }

        if (deliveryBoyInfoDTO.getDeliveryBoyWalletMergerDTO() != null) {
            deliveryBoyInfoEntity.setDeliveryBoyWalletEntity(DeliveryBoyWalletEntity.builder()
                    .deliveryBoyWalletId(deliveryBoyInfoDTO.getDeliveryBoyWalletMergerDTO().getDeliveryBoyWalletId())
                    .currentAmount(deliveryBoyInfoDTO.getDeliveryBoyWalletMergerDTO().getCurrentAmount())
                    .build());
        }

        deliveryBoyInfoEntity.setName(deliveryBoyInfoDTO.getName());
        deliveryBoyInfoEntity.setAvatar(deliveryBoyInfoDTO.getAvatar());
        deliveryBoyInfoEntity.setCitizenshipNo(deliveryBoyInfoDTO.getCitizenshipNo());
        deliveryBoyInfoEntity.setDeliveryBoyId(deliveryBoyInfoDTO.getDeliveryBoyId());
        deliveryBoyInfoEntity.setEmail(deliveryBoyInfoDTO.getEmail());
        deliveryBoyInfoEntity.setLicenseNo(deliveryBoyInfoDTO.getLicenseNo());
        deliveryBoyInfoEntity.setNumberPlateVehicle(deliveryBoyInfoDTO.getNumberPlateVehicle());
        deliveryBoyInfoEntity.setPhone(deliveryBoyInfoDTO.getPhone());
        deliveryBoyInfoEntity.setPassword(deliveryBoyInfoDTO.getPassword());
        deliveryBoyInfoEntity.setStatus(deliveryBoyInfoDTO.getStatus());
        deliveryBoyInfoEntity.setTemporaryAddress(deliveryBoyInfoDTO.getTemporaryAddress());
        deliveryBoyInfoEntity.setPassword(deliveryBoyInfoDTO.getPassword());
        deliveryBoyInfoEntity.setPermanentAddress(deliveryBoyInfoDTO.getPermanentAddress());
        deliveryBoyInfoEntity.setUsername(deliveryBoyInfoDTO.getUsername());
        deliveryBoyRepository.save(deliveryBoyInfoEntity);
        return toDTO(deliveryBoyInfoEntity);
    }

    @Override
    public List<DeliveryBoyIdAndName> getDeliveryBoyIdAndName() {
        return deliveryBoyRepository.getAllBy();
    }


}